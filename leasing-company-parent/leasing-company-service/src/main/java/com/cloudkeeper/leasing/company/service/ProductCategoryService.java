package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.ProductCategory;
import com.cloudkeeper.leasing.base.service.BaseService;

import java.util.Map;

/**
 * 产品分类 service
 * @author asher
 */
public interface ProductCategoryService extends BaseService<ProductCategory> {

    Map<String, String> getIdAndNameMapBySupplierHisId(String supplierHisId);
}