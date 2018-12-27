package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.bean.company.to.GuarantorAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuarantorTO;
import com.cloudkeeper.leasing.bean.company.to.SecuredAssetsTO;
import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.domain.Guarantor;
import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorDTO;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.GuarantorRepository;
import com.cloudkeeper.leasing.company.service.GuarantorService;
import com.cloudkeeper.leasing.company.service.SecuredAssetsService;
import com.cloudkeeper.leasing.company.vo.GuarantorAllVO;
import com.cloudkeeper.leasing.company.vo.GuarantorVO;
import com.cloudkeeper.leasing.company.vo.SecuredAssetsVO;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 担保人 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuarantorServiceImpl extends BaseServiceImpl<Guarantor> implements GuarantorService {

    /** 担保人 repository*/
    private final GuarantorRepository guarantorRepository;

    /** 名下资产 service*/
    private final SecuredAssetsService securedAssetsService;

    /** 用户 manager*/
    private final PrincipalManager principalManager;

    @Override
    protected BaseRepository<Guarantor> getBaseRepository() {
        return guarantorRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }


    @Nonnull
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Guarantor save(@Nonnull GuarantorDTO guarantorDTO, @Nonnull Guarantor guarantor) {
        if (!StringUtils.isEmpty(guarantor.getId())) {
            BeanUtils.copyProperties(guarantorDTO, guarantor, "id");
        } else {
            guarantor = guarantorDTO.convert(Guarantor.class);
        }
        guarantor = save(guarantor);
        //资产
        handleChildren(guarantorDTO, guarantor);
        saveHistory(guarantor);
        return guarantor;
    }


    /**
     * 保存历史记录 担保人 包括子表
     * @param guarantor
     */
    private void saveHistory(Guarantor guarantor) {
        insertHis(guarantor.getId());
        securedAssetsService.saveHistoryList(findAllAssets(guarantor));

    }

    /**
     * 处理资产表（含新增、更新、删除）
     * @param guarantorDTO
     * @param guarantor
     * @return
     */
    private void handleChildren(@Nonnull GuarantorDTO guarantorDTO, @Nonnull Guarantor guarantor) {
        boolean isAdd = guarantorDTO.getId() == null;
        List<SecuredAssetsDTO> assetsList = guarantorDTO.getAssetsList();
        securedAssetsService.saveList(assetsList, guarantor, isAdd);
        securedAssetsService.deleteList(guarantorDTO.getSecuredAssetsDeleteList());
    }

    /**
     * 删除历史记录 担保人 包括子表
     * @param guarantor
     */
    private void deleteHistory(Guarantor guarantor) {
        deleteHis(guarantor.getId());
        List<SecuredAssets> assets = findAllAssets(guarantor);
        securedAssetsService.deleteList(assets.stream().map(SecuredAssets::getId).collect(Collectors.toList()));

    }

    /**
     * 查询所有的资产
     * @param guarantor
     * @return
     */
    private List<SecuredAssets> findAllAssets(Guarantor guarantor) {
        List<SecuredAssets> assets = new ArrayList<>();
        if (!guarantor.getId().isEmpty()) {
            assets = securedAssetsService.findAllByGuarantorId(guarantor.getId());
        }
        return assets;
    }

    /**
     * 重写删除
     * @param id 担保人id
     */
    @Override
    public void deleteById(@Nonnull String id) {
        Guarantor guarantor = findById(id);
        if (guarantor != null) {
            deleteHistory(guarantor);
            super.deleteById(id);
        }
    }

    /**
     * 子表（担保资产）
     * @param guarantorVO  担保人vo
     * @param guarantor 担保人对象
     * @return 子表数据
     */
    private GuarantorAllVO findByGuarantor( GuarantorAllVO guarantorVO, Guarantor guarantor){
        List<SecuredAssets> assetsList = findAllAssets(guarantor);
        if(assetsList == null){
            return null;
        }
        List<SecuredAssetsVO> assetsVOList = new ArrayList<>();
        assetsList.forEach(securedAssets -> assetsVOList.add(securedAssets.convert(SecuredAssetsVO.class)));
        guarantorVO.setAssetsList(assetsVOList);

        return guarantorVO;
    }

    @Override
    public GuarantorAllVO getVo(Guarantor guarantor) {
        GuarantorAllVO guarantorVO = new GuarantorAllVO();
        if (guarantor == null) {
            return null;
        }
        guarantor.setPkId((getHis(GuarantorVO.class, guarantor.getId(), guarantor.getVersion())).getPkId());
        guarantorVO = findByGuarantor(guarantorVO,guarantor);
        BeanUtils.copyProperties(guarantor, guarantorVO);

        return guarantorVO;
    }


    @Override
    public  List<GuarantorAllVO> getListVo(List<Guarantor> guarantors) {
        List<GuarantorAllVO> guarantorAllVOList = new ArrayList<>();
        if (guarantors == null) {
            return null;
        }
        guarantors.forEach(guarantor -> guarantor.setPkId((getHis(GuarantorVO.class, guarantor.getId(), guarantor.getVersion())).getPkId()));
        for (Guarantor guarantor : guarantors) {
            GuarantorAllVO guarantorVO = new GuarantorAllVO();
            BeanUtils.copyProperties(guarantor,guarantorVO);
            guarantorVO = findByGuarantor(guarantorVO,guarantor);
            guarantorAllVOList.add(guarantorVO);
        }
        //客户经理字段
        List<String> ids = new ArrayList<>();
        guarantors.forEach(customer -> ids.add(customer.getCreatedBy()));
        List<PrincipalTO> principalTOList = principalManager.findAllTO(ids);
        for (GuarantorAllVO vo : guarantorAllVOList) {
            for (PrincipalTO to : principalTOList) {
                if (vo.getCreatedBy().equals(to.getId())) { vo.setCustomerManager(to.getName()); }
            }
        }

        return guarantorAllVOList;
    }

    /**
     * @param clazz 表类型
     * @param hisIdList 历史id集合
     * @param <R> 返回类型
     * @return 对应历史集合
     */
    private <R> List<R> getHisListByHisId(@Nonnull Class<R> clazz, @Nonnull List<String> hisIdList) {
        String tableName = getTableName();
        String sql = String.format("SELECT * FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                "WHERE h.pkId in (:hisIdList)", tableName, tableName);
        NativeQuery query = getQuery(clazz, sql).setParameter("hisIdList", hisIdList);
        List<R> list = query.getResultList();
        return list;
    }

    @Override
    public GuarantorAllTO getHisTO(@Nonnull String hisId) {
        GuarantorTO guarantorTO = getHis(GuarantorTO.class, hisId);
        if(guarantorTO == null){
            return null;
        }
        Guarantor guarantor = findById(guarantorTO.getId());
        return handleHis(guarantorTO,guarantor,guarantorTO.getVersion());
    }

    @Override
    public GuarantorAllTO getLastHisTO(@Nonnull String hisId) {
        GuarantorTO guarantorTO = getLatestHisByHisId(GuarantorTO.class, hisId);
        if(guarantorTO == null){
            return null;
        }
        Guarantor guarantor = findById(guarantorTO.getId());
        return handleHis(guarantorTO,guarantor,guarantor.getVersion());
    }

    @Override
    public List<GuarantorTO> getHisListTO(@Nonnull List<String> ids) {
        List<GuarantorTO> toList = getHisListByHisId(GuarantorTO.class, ids);
        if(toList == null){
            return null;
        }
        return toList;
    }

    @Override
    public List<GuarantorTO> getLastHisListTO(@Nonnull List<String> ids) {
        List<GuarantorTO> toList = getLatestHisListByHisId(GuarantorTO.class, ids);
        if(toList == null){
            return null;
        }
        return toList;
    }

    private GuarantorAllTO handleHis(@Nonnull GuarantorTO guarantorTO, @Nonnull Guarantor guarantor, Integer version) {
        GuarantorAllTO allTO = new GuarantorAllTO();
        BeanUtils.copyProperties(guarantorTO,allTO);
        List<SecuredAssets> assetsList = securedAssetsService.findAllByGuarantorId(guarantor.getId());
        allTO.setAssetsTOList(assetsList.stream().map(securedAssets -> securedAssetsService
                .getHis(SecuredAssetsTO.class, securedAssets.getId(), version)).collect(Collectors.toList()));
        return allTO;
    }




}