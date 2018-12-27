package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.bean.company.to.CompanyPersonnelTO;
import com.cloudkeeper.leasing.bean.company.to.SupplierAllTO;
import com.cloudkeeper.leasing.bean.company.to.SupplierTO;
import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.constants.CompanyPersonnelConstant;
import com.cloudkeeper.leasing.company.domain.*;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierDTO;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierSearchable;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.SupplierRepository;
import com.cloudkeeper.leasing.company.service.CommonCompanyService;
import com.cloudkeeper.leasing.company.service.CompanyPersonnelService;
import com.cloudkeeper.leasing.company.service.SupplierService;
import com.cloudkeeper.leasing.company.vo.*;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 供应商 service
 * @author asher
 */
@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements SupplierService {

    /** 供应商 repository */
    @Autowired
    private SupplierRepository supplierRepository;

    /** 客户父表 Service*/
    @Autowired
    private CommonCompanyService commonCompanyService;

    /** 公司人员 Service*/
    @Autowired
    private CompanyPersonnelService companyPersonnelService;

    /** 用户 manager*/
    @Autowired
    private  PrincipalManager principalManager;

    @Override
    protected BaseRepository<Supplier> getBaseRepository() {
        return supplierRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    @Transactional
    public Supplier save(@Nonnull SupplierDTO supplierDTO, Supplier supplier) {
        CommonCompany commonCompany;
        if (!StringUtils.isEmpty(supplier.getId())) {
            //更新
            commonCompany = supplier.getCommonCompany();
            BeanUtils.copyProperties(supplierDTO, commonCompany, "id");
        } else {
            // 新增
            commonCompany = supplierDTO.convert(CommonCompany.class);
        }
        commonCompany = commonCompanyService.save(commonCompany);
        supplierDTO.setParentId(commonCompany.getId());
        BeanUtils.copyProperties(supplierDTO, supplier);
        supplier.setCommonCompany(commonCompany);
        supplier = super.save(supplier);
        handleChildren(supplierDTO, supplier);
        saveHistory(supplier);
        return supplier;
    }

    private Supplier handleChildren(SupplierDTO supplierDTO, Supplier supplier) {
        boolean isAdd = supplierDTO.getId() == null;
        CompanyPersonnelDTO corporationDTO = supplierDTO.getCorporation();
        supplier.setCorporation(companyPersonnelService.saveList(Arrays.asList(corporationDTO), supplier, isAdd).get(0));

        supplier.setContactList(companyPersonnelService.saveList(supplierDTO.getContactList(), supplier, isAdd));
        companyPersonnelService.deleteList(supplierDTO.getContactDeleteList());
        return supplier;
    }

    private void saveHistory(Supplier supplier) {
        commonCompanyService.insertHis(supplier.getParentId());
        insertHis(supplier.getId());

        companyPersonnelService.insertHis(supplier.getCorporation().getId());
        companyPersonnelService.saveHistoryList(supplier.getContactList());
    }

    @Override
    public Supplier getOne(@Nonnull String id) {
        Optional<Supplier> supplierOptional = findOptionalById(id);
        if (!supplierOptional.isPresent()) {
            return null;
        }
        return getOne(supplierOptional.get());
    }

    private Supplier getOne(@Nonnull Supplier supplier) {
        String id = supplier.getId();

        List<CompanyPersonnel> corporationList = companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.CORPORATION);
        if (!corporationList.isEmpty()) {
            supplier.setCorporation(corporationList.get(0));
        }

        supplier.setContactList(companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.NATURAL));
        return supplier;
    }

    @Override
    public void deleteById(@Nonnull String id) {
        Optional<Supplier> supplierOptional = findOptionalById(id);
        if (!supplierOptional.isPresent()) {
            return;
        }
        Supplier supplier = supplierOptional.get();
        deleteHistory(getOne(supplier));
        supplierRepository.deleteById(id);
        commonCompanyService.deleteById(supplier.getParentId());
    }

    private void deleteHistory(Supplier supplier) {
        commonCompanyService.deleteHis(supplier.getParentId());
        deleteHis(supplier.getId());
        List<CompanyPersonnel> contactList = supplier.getContactList();
        contactList.add(supplier.getCorporation());
        companyPersonnelService.deleteList(contactList.stream().map(CompanyPersonnel::getId).collect(Collectors.toList()));
    }

    @Override
    public List<SupplierAllVO> toVO(List<Supplier> suppliers) {
        List<SupplierAllVO> customerVOList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        suppliers.forEach(supplier -> customerVOList.add(getOne(supplier).convert()));
        suppliers.forEach(customer -> ids.add(customer.getCreatedBy()));
        List<PrincipalTO> principalTOList = principalManager.findAllTO(ids);

        for (SupplierAllVO vo : customerVOList) {
            for (PrincipalTO to : principalTOList) {
                if (vo.getCreatedBy().equals(to.getId())) { vo.setCustomerManager(to.getName()); }
            }
        }
        return customerVOList;
    }

    @Override
    public BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable) {
        SupplierSearchable supplierSearchable = (SupplierSearchable) searchable;
        QSupplier qSupplier = QSupplier.supplier;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(supplierSearchable.getName())) {
            booleanBuilder.and(qSupplier.commonCompany.name.contains(supplierSearchable.getName()));
        }
        return booleanBuilder;
    }

    @Override
    public SupplierAllVO getHisVO(String hisId) {
        SupplierVO supplierVO = commonCompanyService.getChildren(SupplierVO.class, hisId, getTableName());
        if (supplierVO == null) {
            return null;
        }
        Supplier supplier = getOne(supplierVO.getId());

        return handleHis(supplier, supplierVO);
    }

    @Override
    public SupplierAllVO getHisVOByBusinessId(String id) {
        Supplier supplier = getOne(id);
        if (supplier == null) {
            return null;
        }
        SupplierVO supplierVO = commonCompanyService.getChildrenByBusinessId(SupplierVO.class, id, getTableName());

        return handleHis(supplier, supplierVO);
    }

    /**
     * 处理父子历史记录
     * @param supplier 供应商
     * @param supplierVO 供应商vo
     */
    private SupplierAllVO handleHis(Supplier supplier, SupplierVO supplierVO) {
        SupplierAllVO supplierAllVO = new SupplierAllVO();
        BeanUtils.copyProperties(supplierVO, supplierAllVO);

        Integer version = supplier.getVersion();
        supplierAllVO.setContactList(supplier.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelVO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        supplierAllVO.setCorporation(companyPersonnelService.getHis(CompanyPersonnelVO.class, supplier.getCorporation().getId(), version));
        return supplierAllVO;
    }


    @Override
    public SupplierAllTO getHisTO(String hisId) {
        SupplierTO supplierTO = commonCompanyService.getChildren(SupplierTO.class, hisId, getTableName());
        if (supplierTO == null) {
            return null;
        }
        Supplier supplier = getOne(supplierTO.getId());

        return handleHis(supplier, supplierTO);
    }

    @Override
    public SupplierAllTO getHisTOByBusinessId(String businessId) {
        Supplier supplier = getOne(businessId);
        if (supplier == null) {
            return null;
        }
        SupplierTO supplierTO = commonCompanyService.getChildrenByBusinessId(SupplierTO.class, businessId, getTableName());

        return handleHis(supplier, supplierTO);
    }

    /**
     * 处理父子历史记录
     * @param supplier 供应商
     * @param supplierTO 供应商to
     */
    private SupplierAllTO handleHis(Supplier supplier, SupplierTO supplierTO) {
        SupplierAllTO supplierAllTO = new SupplierAllTO();
        BeanUtils.copyProperties(supplierTO, supplierAllTO);

        Integer version = supplier.getVersion();
        supplierAllTO.setContactList(supplier.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        supplierAllTO.setCorporation(companyPersonnelService.getHis(CompanyPersonnelTO.class, supplier.getCorporation().getId(), version));
        return supplierAllTO;
    }

    @Override
    public SupplierAllTO getLatestHisByHisId(@Nonnull String hisId) {
        SupplierTO supplierTO = commonCompanyService.getLatestHisByHisId(SupplierTO.class, hisId, getTableName());
        if (supplierTO == null) {
            return null;
        }
        Supplier supplier = getOne(supplierTO.getId());

        return handleHis(supplier, supplierTO);
    }
}