package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.ProductModel;
import com.cloudkeeper.leasing.company.repository.ProductModelRepository;
import com.cloudkeeper.leasing.company.service.ProductModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * 产品型号 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductModelServiceImpl extends BaseServiceImpl<ProductModel> implements ProductModelService {

    /** 产品型号 repository */
    private final ProductModelRepository productModelRepository;

    @Override
    protected BaseRepository<ProductModel> getBaseRepository() {
        return productModelRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("unit", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("currency", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    public ProductModel save(@Nonnull ProductModel entity) {
        entity = super.save(entity);
        insertHis(entity.getId());
        return entity;
    }

    @Override
    public Map<String, String> getIdAndNameMapByCategoryHisId(String categoryHisId) {
        String sql = String.format("SELECT cpch.pkId, cpc.name FROM l_cp_product_model cpc LEFT JOIN l_cp_product_model_his cpch ON cpc.id = cpch.id AND " +
                "cpc.version = cpch.version WHERE cpc.categoryId = (SELECT id From l_cp_product_category_his WHERE pkId = '%s') ", categoryHisId);
        return getValueMap(sql);
    }
}