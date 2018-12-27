package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.base.utils.BeanConverts;
import com.cloudkeeper.leasing.bean.company.to.*;
import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.constants.CompanyPersonnelConstant;
import com.cloudkeeper.leasing.company.domain.*;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanyDTO;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanySearchable;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.GuaranteeCompanyRepository;
import com.cloudkeeper.leasing.company.service.CommonCompanyService;
import com.cloudkeeper.leasing.company.service.CompanyPersonnelService;
import com.cloudkeeper.leasing.company.service.GuaranteeCompanyService;
import com.cloudkeeper.leasing.company.service.SecuredAssetsService;
import com.cloudkeeper.leasing.company.vo.*;
import com.querydsl.core.BooleanBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 担保公司 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuaranteeCompanyServiceImpl extends BaseServiceImpl<GuaranteeCompany> implements GuaranteeCompanyService {

    /** 担保公司 repository*/
    private final GuaranteeCompanyRepository guaranteeCompanyRepository;

    /** 客户父表 Service*/
    private final CommonCompanyService commonCompanyService;

    /** 法人、联系人、股东董事 Service*/
    private final CompanyPersonnelService companyPersonnelService;

    /** 名下资产 Service*/
    private final SecuredAssetsService securedAssetsService;

    /** 用户 manager*/
    private final PrincipalManager principalManager;


    @Override
    protected BaseRepository<GuaranteeCompany> getBaseRepository() {
        return guaranteeCompanyRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GuaranteeCompany save(@Nonnull GuaranteeCompanyDTO guaranteeCompanyDTO, @Nonnull GuaranteeCompany guaranteeCompany) {
        CommonCompany commonCompany;
        if (!StringUtils.isEmpty(guaranteeCompany.getId())) {
            //更新
            commonCompany = guaranteeCompany.getCommonCompany();
            BeanUtils.copyProperties(guaranteeCompanyDTO, commonCompany, "id");
        } else {
            // 新增
            commonCompany = guaranteeCompanyDTO.convert(CommonCompany.class);
        }
        commonCompany = commonCompanyService.save(commonCompany);
        guaranteeCompanyDTO.setParentId(commonCompany.getId());
        GuaranteeCompany guarantee = guaranteeCompanyDTO.convert(GuaranteeCompany.class);
        guarantee.setCommonCompany(commonCompany);
        guarantee = super.save(guarantee);
        handleChildren(guaranteeCompanyDTO, guarantee);
        saveHistory(guarantee);
        return guarantee;
    }


    /**
     * 处理客户子表（含新增、更新、删除）
     * @param guaranteeCompanyDTO
     * @param guaranteeCompany
     * @return
     */
    private GuaranteeCompany handleChildren(@Nonnull GuaranteeCompanyDTO guaranteeCompanyDTO, @Nonnull GuaranteeCompany guaranteeCompany) {
        boolean isAdd = guaranteeCompanyDTO.getId() == null;
        CompanyPersonnelDTO corporationDTO = guaranteeCompanyDTO.getCorporation();
        guaranteeCompany.setCorporation(companyPersonnelService.saveList(Arrays.asList(corporationDTO), guaranteeCompany, isAdd).get(0));

        List<CompanyPersonnelDTO> contactList = guaranteeCompanyDTO.getContactList();
        guaranteeCompany.setContactList(companyPersonnelService.saveList(contactList, guaranteeCompany, isAdd));
        companyPersonnelService.deleteList(guaranteeCompanyDTO.getContactDeleteList());

        List<CompanyPersonnelDTO> shareholderList = guaranteeCompanyDTO.getShareholderList();
        guaranteeCompany.setShareholderList(companyPersonnelService.saveList(shareholderList, guaranteeCompany, isAdd));
        companyPersonnelService.deleteList(guaranteeCompanyDTO.getShareholderDeleteList());

        List<SecuredAssetsDTO> securedAssetsList = guaranteeCompanyDTO.getSecuredAssetsList();
        guaranteeCompany.setAssetsList(securedAssetsService.saveList(securedAssetsList, guaranteeCompany, isAdd));
        securedAssetsService.deleteList(guaranteeCompanyDTO.getSecuredAssetsDeleteList());

        return guaranteeCompany;
    }


    /**
     * 保存历史记录  包括子表（普通联系人、股东、法人、担保资产）
     * @param guaranteeCompany
     */
    private void saveHistory(GuaranteeCompany guaranteeCompany) {
        commonCompanyService.insertHis(guaranteeCompany.getParentId());
        insertHis(guaranteeCompany.getId());

        List<CompanyPersonnel> companyPersonnelList = guaranteeCompany.getContactList();
        companyPersonnelList.addAll(guaranteeCompany.getShareholderList());
        companyPersonnelList.add(guaranteeCompany.getCorporation());
        companyPersonnelService.saveHistoryList(companyPersonnelList);

        securedAssetsService.saveHistoryList(guaranteeCompany.getAssetsList());
    }


    /**
     * 删除历史记录 包括子表（普通联系人、股东、法人、担保资产）
     * @param guaranteeCompany
     */
    private void deleteHistory(@NonNull GuaranteeCompany guaranteeCompany) {
        commonCompanyService.deleteHis(guaranteeCompany.getParentId());
        deleteHis(guaranteeCompany.getId());

        List<CompanyPersonnel> companyPersonnelList = guaranteeCompany.getContactList();
        companyPersonnelList.addAll(guaranteeCompany.getShareholderList());
        companyPersonnelList.add(guaranteeCompany.getCorporation());
        companyPersonnelService.deleteList(companyPersonnelList.stream().map(CompanyPersonnel::getId).collect(Collectors.toList()));

        securedAssetsService.deleteList(guaranteeCompany.getAssetsList().stream().map(SecuredAssets::getId).collect(Collectors.toList()));
    }

    @Override
    public void deleteById(@Nonnull String id) {
        GuaranteeCompany guaranteeCompany = findById(id);
        if (guaranteeCompany == null) {
            return;
        }
        deleteHistory(guaranteeCompany);
        guaranteeCompanyRepository.deleteById(id);
        commonCompanyService.deleteById(guaranteeCompany.getParentId());
    }


    /**
     * 查询（法人、普通联系人、股东、担保资产）
     * @param id
     * @return 担保公司
     */
    @Override
    public GuaranteeCompany findById(@Nonnull String id) {
        GuaranteeCompany guaranteeCompany = super.findById(id);
        if (guaranteeCompany == null) {
            return null;
        }
        guaranteeCompany.setPkId((getHis(GuaranteeCompanyVO.class, id, guaranteeCompany.getVersion())).getPkId());
        List<CompanyPersonnel> corporationList = companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.CORPORATION);
        if (!corporationList.isEmpty()) {
            guaranteeCompany.setCorporation(corporationList.get(0));
        }
        guaranteeCompany.setCommonCompany(guaranteeCompany.getCommonCompany());
        guaranteeCompany.setContactList(companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.NATURAL));
        guaranteeCompany.setShareholderList(companyPersonnelService.findAllByAndCompanyIdAndType(id, CompanyPersonnelConstant.SHAREHOLDER));
        guaranteeCompany.setAssetsList(securedAssetsService.findAllByGuarantorId(id));

        return guaranteeCompany;
    }


    @Override
    public List<GuaranteeCompanyAllVO> toVO(List<GuaranteeCompany> guaranteeCompanies) {
        List<GuaranteeCompanyAllVO> companyVOList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        guaranteeCompanies.forEach(guaranteeCompany -> companyVOList.add(findById(guaranteeCompany.getId()).convert()));
        guaranteeCompanies.forEach(customer -> ids.add(customer.getCreatedBy()));
        List<PrincipalTO> principalTOList = principalManager.findAllTO(ids);

        for (GuaranteeCompanyAllVO vo : companyVOList) {
            for (PrincipalTO to : principalTOList) {
                if (vo.getCreatedBy().equals(to.getId())) { vo.setCustomerManager(to.getName()); }
            }
        }
        return companyVOList;
    }

    @Override
    public BooleanBuilder findByBuilder(GuaranteeCompanySearchable searchable) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(searchable.getName())) {
            booleanBuilder.and(QGuaranteeCompany.guaranteeCompany.commonCompany.name.contains(searchable.getName()));
        }
        if (StringUtils.hasText(searchable.getUniformCreditCode())) {
            booleanBuilder.and(QGuaranteeCompany.guaranteeCompany.commonCompany.uniformCreditCode.contains(searchable.getUniformCreditCode()));
        }
        return booleanBuilder;
    }
    
    @Override
    public GuaranteeCompanyAllTO getHisTO(@Nonnull String hisId) {
        GuaranteeCompanyTO companyTO = commonCompanyService.getChildren(GuaranteeCompanyTO.class,hisId,getTableName());
        if(companyTO == null){
            return null;
        }
        GuaranteeCompany company = findById(companyTO.getId());

        return handleHis(companyTO,company,companyTO.getVersion());
    }

    @Override
    public GuaranteeCompanyAllTO getLastHisTO(@Nonnull String hisId) {
        GuaranteeCompanyTO companyTO = commonCompanyService.getLatestHisByHisId(GuaranteeCompanyTO.class,hisId,getTableName());
        if(companyTO == null){
            return null;
        }
        GuaranteeCompany company = findById(companyTO.getId());

        return handleHis(companyTO,company,company.getVersion());
    }


    @Override
    public List<GuaranteeCompanyAllTO> getHisListTO(@Nonnull List<String> ids) {
        List<GuaranteeCompanyTO> toList = commonCompanyService.getHisListByHisId(GuaranteeCompanyTO.class, ids, getTableName());
        if(toList == null){
            return null;
        }
        List<GuaranteeCompanyAllTO> allTO = toList.stream().map(guaranteeCompanyTO -> handleHis(guaranteeCompanyTO,findById(guaranteeCompanyTO.getId()),
                guaranteeCompanyTO.getVersion())).collect(Collectors.toList());
        return allTO;
    }

    @Override
    public List<GuaranteeCompanyAllTO> getLastHisListTO(@Nonnull List<String> ids) {
        List<GuaranteeCompanyTO> toList = commonCompanyService.getLatestHisListByHisId(GuaranteeCompanyTO.class, ids, getTableName());
        if(toList == null){
            return null;
        }
        List<GuaranteeCompanyAllTO> allTO = toList.stream().map(guaranteeCompanyTO -> handleHis(guaranteeCompanyTO,findById(guaranteeCompanyTO.getId()),
                findById(guaranteeCompanyTO.getId()).getVersion())).collect(Collectors.toList());
        return allTO;
    }


    private GuaranteeCompanyAllTO handleHis(@Nonnull GuaranteeCompanyTO companyTO, @Nonnull GuaranteeCompany company,Integer version) {
        GuaranteeCompanyAllTO allTO = new GuaranteeCompanyAllTO();
        BeanUtils.copyProperties(companyTO,allTO);
        allTO.setCorporation(companyPersonnelService.getHis(CompanyPersonnelTO.class,company.getCorporation().getId(),version));
        allTO.setContactList(company.getContactList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        allTO.setShareholderList(company.getShareholderList().stream().map(companyPersonnel -> companyPersonnelService
                .getHis(CompanyPersonnelTO.class, companyPersonnel.getId(), version)).collect(Collectors.toList()));
        allTO.setSecuredAssetsList(company.getAssetsList().stream().map(securedAssets -> securedAssetsService
                .getHis(SecuredAssetsTO.class, securedAssets.getId(), version)).collect(Collectors.toList()));
        return allTO;
    }


}