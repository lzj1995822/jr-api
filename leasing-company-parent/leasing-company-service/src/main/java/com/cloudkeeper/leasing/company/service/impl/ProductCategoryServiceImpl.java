package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.ProductCategory;
import com.cloudkeeper.leasing.company.repository.ProductCategoryRepository;
import com.cloudkeeper.leasing.company.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * 产品分类 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService {

    /** 产品分类 repository */
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    protected BaseRepository<ProductCategory> getBaseRepository() {
        return productCategoryRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("supportCode", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("originType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("supplierId", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    public ProductCategory save(@Nonnull ProductCategory entity) {
        entity = super.save(entity);
        insertHis(entity.getId());
        return entity;
    }

    @Override
    public Map<String, String> getIdAndNameMapBySupplierHisId(String supplierHisId) {
        String sql = String.format("SELECT cpch.pkId, cpc.name FROM l_cp_product_category cpc LEFT JOIN l_cp_product_category_his cpch ON cpc.id = cpch.id AND " +
                "cpc.version = cpch.version WHERE cpc.supplierId = (SELECT id From l_cp_supplier_his WHERE pkId = '%s') ", supplierHisId);
        return getValueMap(sql);
    }
}