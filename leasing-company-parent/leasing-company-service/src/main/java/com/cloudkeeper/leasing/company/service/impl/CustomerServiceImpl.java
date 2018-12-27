package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.bean.company.to.*;
import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.constants.CompanyPersonnelConstant;
import com.cloudkeeper.leasing.company.domain.*;
import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountDTO;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.customer.CustomerDTO;
import com.cloudkeeper.leasing.company.dto.customer.CustomerSearchable;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailDTO;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.CustomerRepository;
import com.cloudkeeper.leasing.company.service.*;
import com.cloudkeeper.leasing.company.vo.*;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户 service
 * @author jerry
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {

    /** 用户 repository*/
    private final CustomerRepository customerRepository;

    /** 客户父表 Service*/
    private final CommonCompanyService commonCompanyService;

    /** 法人、联系人、股东董事 Service*/
    private final CompanyPersonnelService companyPersonnelService;

    /** 客户详细、开票资料 Service*/
    private final CustomerDetailService customerDetailService;

    /** 银行账户 Service*/
    private final BankAccountService bankAccountService;

    /** 用户 manager*/
    private final PrincipalManager principalManager;

    @Override
    protected BaseRepository<Customer> getBaseRepository() {
        return customerRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("createdBy", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public Page<Customer> findAll(@Nonnull BaseSearchable searchable, @Nonnull Pageable pageable) {
        Page<Customer> customerPage = super.findAll(defaultExample(searchable), pageable);
        customerPage.getContent().forEach(customer -> customer = getOne(customer));
        return customerPage;
    }

    @Nonnull
    @Override
    @Transactional
    public Customer save(@Nonnull CustomerDTO customerDTO, Customer customer) {
        CommonCompany commonCompany;
        if (!StringUtils.isEmpty(customer.getId())) {
            //更新
            commonCompany = customer.getCommonCompany();
            BeanUtils.copyProperties(customerDTO, commonCompany, "id");
        } else {
            // 新增
            commonCompany = customerDTO.convert(CommonCompany.class);
        }
        commonCompany = commonCompanyService.save(commonCompany);
        customerDTO.setParentId(commonCompany.getId());
        BeanUtils.copyProperties(customerDTO, customer);
        customer.setCommonCompany(commonCompany);
        customer = super.save(customer);
        handleChildren(customerDTO, customer);
        saveHistory(customer);
        return customer;
    }

    /**
     * 处理客户子表（含新增、更新、删除）
     * @param customerDTO 客户DTO
     * @param customer 客户
     */
    private void handleChildren(@Nonnull CustomerDTO customerDTO, @Nonnull Customer customer) {
        boolean isAdd = customerDTO.getId() == null;

        CompanyPersonnelDTO corporationDTO = customerDTO.getCorporation();
        customer.setCorporation(companyPersonnelService.saveList(Arrays.asList(corporationDTO), customer, isAdd).get(0));

        List<CompanyPersonnelDTO> contactList = customerDTO.getContactList();
        customer.setContactList(companyPersonnelService.saveList(contactList, customer, isAdd));
        companyPersonnelService.deleteList(customerDTO.getContactDeleteList());

        List<CompanyPersonnelDTO> shareholderList = customerDTO.getShareholderList();
        customer.setShareholderList(companyPersonnelService.saveList(shareholderList, customer, isAdd));
        companyPersonnelService.deleteList(customerDTO.getShareholderDeleteList());

        List<BankAccountDTO> bankAccountList = customerDTO.getBankAccountList();
        customer.setBankAccountList(bankAccountService.saveList(bankAccountList, customer, isAdd));
        bankAccountService.deleteList(customerDTO.getBankAccountDeleteList());

        List<CustomerDetailDTO> customerDetailList = customerDTO.getCustomerDetailList();
        customer.setCustomerDetailList(customerDetailService.saveList(customerDetailList, customer, isAdd));
        customerDetailService.deleteList(customerDTO.getCustomerDetailDeleteList());
    }

    /**
     * 保存历史记录 客户 包括子表
     * @param customer 客户
     */
    private void saveHistory(@Nonnull Customer customer) {
        commonCompanyService.insertHis(customer.getParentId());
        insertHis(customer.getId());

        List<CompanyPersonnel> companyPersonnelList = customer.getContactList();
        companyPersonnelList.addAll(customer.getShareholderList());
        companyPersonnelList.add(customer.getCorporation());
        companyPersonnelService.saveHistoryList(companyPersonnelList);

        bankAccountService.saveHistoryList(customer.getBankAccountList());

        customerDetailService.saveHistoryList(customer.getCustomerDetailList());
    }

    /**
     * 删除历史记录 客户 包括子表
     * @param customer 客户
     */
    private void deleteHistory(@Nonnull Customer customer) {
        commonCompanyService.deleteHis(customer.getParentId());
        deleteHis(customer.getId());

        List<CompanyPersonnel> companyPersonnelList = customer.getContactList();
        companyPersonnelList.addAll(customer.getShareholderList());
        companyPersonnelList.add(customer.getCorporation());
        commonCompanyService.deleteList(companyPersonnelList.stream().map(CompanyPersonnel::getId).collect(Collectors.toList()));

        bankAccountService.deleteList(customer.getBankAccountList().stream().map(BankAccount::getId).collect(Collectors.toList()));

        customerDetailService.deleteList(customer.getCustomerDetailList().stream().map(CustomerDetail::getId).collect(Collectors.toList()));
    }

    @Override
    public void deleteById(@Nonnull String id) {
        Optional<Customer> customerOptional = findOptionalById(id);
        if (!customerOptional.isPresent()) {
            return;
        }
        Customer customer = customerOptional.get();
        deleteHistory(customer);
        customerRepository.deleteById(id);
        commonCompanyService.deleteById(customer.getParentId());
    }

    @Override
    public Customer getOne(@Nonnull String id) {
        Optional<Customer> customerOptional = findOptionalById(id);
        if (!customerOptional.isPresent()) {
            return null;
        }
        return getOne(customerOptional.get());
    }

    /**
     * 获取所有关联的子表信息
     * @param customer 客户
     * @return
     */
    private Customer getOne(@Nonnull Customer customer) {
        String id = customer.getId();
        List<CompanyPersonnel> corporationList = companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.CORPORATION);
        if (!corporationList.isEmpty()) {
            customer.setCorporation(corporationList.get(0));
        }

        customer.setContactList(companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.NATURAL));

        customer.setShareholderList(companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.SHAREHOLDER));

        customer.setCustomerDetailList(customerDetailService.findAllByCustomerId(id));

        customer.setBankAccountList(bankAccountService.findAllByCustomerId(id));

        return customer;
    }


    @Override
    public List<CustomerAllVO> toVO(List<Customer> customers) {
        List<CustomerAllVO> customerVOList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        customers.forEach(customer -> customerVOList.add(getOne(customer).convert()));
        customers.forEach(customer -> ids.add(customer.getCreatedBy()));
        List<PrincipalTO> principalTOList = principalManager.findAllTO(ids);

        for (CustomerAllVO vo : customerVOList) {
            for (PrincipalTO to : principalTOList) {
                if (vo.getCreatedBy().equals(to.getId())) { vo.setCustomerManager(to.getName()); }
            }
        }
        return customerVOList;
    }


    @Nonnull
    @Override
    @Transactional
    public Customer save(@Nonnull Customer customer) {
        customer = getOne(customer);
        commonCompanyService.save(customer.getCommonCompany());
        customer = super.save(customer);
        handleChildren(customer);
        return customer;
    }

    /**
     * 处理客户子表（含新增、更新、删除）
     * @param customer 客户
     * @return
     */
    private void handleChildren(@Nonnull Customer customer) {
        customer.setCorporation(companyPersonnelService.save(customer.getCorporation()));
        customer.setContactList(companyPersonnelService.saveList(customer.getContactList()));
        customer.setShareholderList(companyPersonnelService.saveList(customer.getShareholderList()));
        customer.setBankAccountList(bankAccountService.saveList(customer.getBankAccountList()));
        customer.setCustomerDetailList(customerDetailService.saveList(customer.getCustomerDetailList()));
    }

    @Override
    public BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable) {
        CustomerSearchable customerSearchable = (CustomerSearchable) searchable;
        QCustomer qCustomer = QCustomer.customer;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(customerSearchable.getName())) {
            booleanBuilder.and(qCustomer.commonCompany.name.contains(customerSearchable.getName()));
        }
        if (StringUtils.hasText(customerSearchable.getUniformCreditCode())) {
            booleanBuilder.and(qCustomer.commonCompany.uniformCreditCode.contains(customerSearchable.getUniformCreditCode()));
        }
        if (StringUtils.hasText(customerSearchable.getCategory())) {
            booleanBuilder.and(qCustomer.commonCompany.category.contains(customerSearchable.getCategory()));
        }
        if (StringUtils.hasText(customerSearchable.getCreatorId())) {
            booleanBuilder.and(qCustomer.createdBy.eq(customerSearchable.getCreatorId()));
        }
        return booleanBuilder;
    }

    @Override
    public CustomerAllVO getHisVO(@Nonnull String hisId) {
        CustomerVO customerVO = commonCompanyService.getChildren(CustomerVO.class, hisId, getTableName());
        if (customerVO == null) {
            return null;
        }
        Customer customer = getOne(customerVO.getId());

        return handleHis(customer, customerVO);
    }

    @Override
    public CustomerAllVO getHisVOByBusinessId(String businessId) {
        Customer customer = getOne(businessId);
        if (customer == null) {
            return null;
        }
        CustomerVO customerVO = commonCompanyService.getChildrenByBusinessId(CustomerVO.class, businessId, getTableName());

        return handleHis(customer, customerVO);
    }

    private CustomerAllVO handleHis(Customer customer, @Nonnull CustomerVO customerVO) {
        CustomerAllVO customerAllVO = new CustomerAllVO();
        BeanUtils.copyProperties(customerVO, customerAllVO);
        Integer version = customer.getVersion();
        customerAllVO.setCorporation(companyPersonnelService.getHis(customer.getCorporation().getId(), version));
        customerAllVO.setContactList(customer.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelVO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllVO.setBankAccountList(customer.getBankAccountList().stream().map(bankAccount -> bankAccountService
                .getHis(BankAccountVO.class, bankAccount.getId(), version)).collect(Collectors.toList()));
        customerAllVO.setContactList(customer.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelVO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllVO.setShareholderList(customer.getShareholderList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelVO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllVO.setCustomerDetailList(customer.getCustomerDetailList().stream().map(customerDetail -> customerDetailService
                .getHis(CustomerDetailVO.class, customerDetail.getId(), version)).collect(Collectors.toList()));
        return customerAllVO;
    }

    @Override
    public CustomerAllTO getHisTOByBusinessId(String businessId) {
        Customer customer = getOne(businessId);
        if (customer == null) {
            return null;
        }
        CustomerTO customerTO = commonCompanyService.getChildrenByBusinessId(CustomerTO.class, businessId, getTableName());

        return handleHis(customer, customerTO);
    }

    @Override
    public CustomerAllTO getHisTO(@Nonnull String hisId) {
        CustomerTO customerTO = commonCompanyService.getChildren(CustomerTO.class, hisId, getTableName());
        if (customerTO == null) {
            return null;
        }
        Customer customer = getOne(customerTO.getId());
        customer.setVersion(customerTO.getVersion());

        return handleHis(customer, customerTO);
    }

    private CustomerAllTO handleHis(Customer customer, @Nonnull CustomerTO customerTO) {
        CustomerAllTO customerAllTO = new CustomerAllTO();
        BeanUtils.copyProperties(customerTO, customerAllTO);
        Integer version = customer.getVersion();
        customerAllTO.setCorporation(companyPersonnelService.getHis(CompanyPersonnelTO.class, customer.getCorporation().getId(), version));
        customerAllTO.setContactList(customer.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllTO.setBankAccountList(customer.getBankAccountList().stream().map(bankAccount -> bankAccountService
                .getHis(BankAccountTO.class, bankAccount.getId(), version)).collect(Collectors.toList()));
        customerAllTO.setContactList(customer.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllTO.setShareholderList(customer.getShareholderList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        customerAllTO.setCustomerDetailList(customer.getCustomerDetailList().stream().map(customerDetail -> customerDetailService
                .getHis(CustomerDetailTO.class, customerDetail.getId(), version)).collect(Collectors.toList()));
        return customerAllTO;
    }

    @Override
    public CustomerAllTO getLatestHisByHisId(@Nonnull String hisId) {
        CustomerTO customerTO = commonCompanyService.getLatestHisByHisId(CustomerTO.class, hisId, getTableName());
        if (customerTO == null) {
            return null;
        }
        Customer customer = getOne(customerTO.getId());

        return handleHis(customer, customerTO);
    }
}
