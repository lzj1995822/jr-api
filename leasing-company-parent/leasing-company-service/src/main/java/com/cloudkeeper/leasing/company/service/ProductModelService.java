package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.ProductModel;
import com.cloudkeeper.leasing.base.service.BaseService;

import java.util.Map;

/**
 * 产品型号 service
 * @author asher
 */
public interface ProductModelService extends BaseService<ProductModel> {

    /**
     * 获取id-name下来集合
     * @return
     */
    Map<String, String> getIdAndNameMapByCategoryHisId(String categoryHisId);
}