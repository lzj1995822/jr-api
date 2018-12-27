package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.vo.SecuredAssetsVO;
import lombok.NonNull;

import java.util.List;

/**
 * 担保资产 service
 * @author asher
 */
public interface SecuredAssetsService extends BaseService<SecuredAssets> {

    /**
     * 批量保存并返回voList
     * @param assetsList
     * @param baseEntity
     * @return
     */
    List<SecuredAssets> saveList(List<SecuredAssetsDTO> assetsList,BaseEntity baseEntity, Boolean isAdd);

    /**
     * 查询并返回volist
     * @param guarantorId 担保资产id
     * @return 担保资产集合
     */
    List<SecuredAssets>  findAllByGuarantorId(@NonNull String guarantorId);

}