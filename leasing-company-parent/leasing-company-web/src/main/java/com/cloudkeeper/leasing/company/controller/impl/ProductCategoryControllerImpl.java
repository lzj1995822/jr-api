package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.ProductCategoryTO;
import com.cloudkeeper.leasing.company.controller.ProductCategoryController;
import com.cloudkeeper.leasing.company.domain.ProductCategory;
import com.cloudkeeper.leasing.company.dto.productcategory.ProductCategoryDTO;
import com.cloudkeeper.leasing.company.dto.productcategory.ProductCategorySearchable;
import com.cloudkeeper.leasing.company.service.ProductCategoryService;
import com.cloudkeeper.leasing.company.vo.ProductCategoryVO;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 产品分类 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductCategoryControllerImpl implements ProductCategoryController {

    /** 产品分类 service */
    private final ProductCategoryService productCategoryService;

    @Override
    public Result<ProductCategoryVO> findOne(@ApiParam(value = "产品分类id", required = true) @PathVariable String id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryService.findOptionalById(id);
        return productCategoryOptional.map(productCategory -> Result.of(productCategory.convert(ProductCategoryVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<ProductCategoryVO> add(@ApiParam(value = "产品分类 DTO", required = true) @RequestBody @Validated ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = productCategoryService.save(productCategoryDTO.convert(ProductCategory.class));
        return Result.ofAddSuccess(productCategory.convert(ProductCategoryVO.class));
    }

    @Override
    public Result<ProductCategoryVO> update(@ApiParam(value = "产品分类id", required = true) @PathVariable String id,
        @ApiParam(value = "产品分类 DTO", required = true) @RequestBody @Validated ProductCategoryDTO productCategoryDTO) {
        Optional<ProductCategory> productCategoryOptional = productCategoryService.findOptionalById(id);
        if (!productCategoryOptional.isPresent()) {
            return Result.ofLost();
        }
        ProductCategory productCategory = productCategoryOptional.get();
        BeanUtils.copyProperties(productCategoryDTO, productCategory);
        productCategory = productCategoryService.save(productCategory);
        return Result.ofUpdateSuccess(productCategory.convert(ProductCategoryVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "产品分类id", required = true) @PathVariable String id) {
        productCategoryService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<ProductCategoryVO>> list(@ApiParam(value = "产品分类查询条件", required = true) @RequestBody ProductCategorySearchable productCategorySearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<ProductCategory> productCategoryList = productCategoryService.findAll(productCategorySearchable, sort);
        List<ProductCategoryVO> productCategoryVOList = productCategoryList.stream().map(ProductCategory::convert).collect(Collectors.toList());
        return Result.of(productCategoryVOList);
    }

    @Override
    public Result<Page<ProductCategoryVO>> page(@ApiParam(value = "产品分类查询条件", required = true) @RequestBody ProductCategorySearchable productCategorySearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<ProductCategory> productCategoryPage = productCategoryService.findAll(productCategorySearchable, pageable);
        List<ProductCategoryVO> collect = productCategoryPage.getContent().stream().map(ProductCategory::convert).collect(Collectors.toList());
        Page<ProductCategoryVO> productCategoryVOPage = new PageImpl<>(collect, pageable, productCategoryPage.getTotalElements());
        return Result.of(productCategoryVOPage);
    }

    @Override
    public Result<Map<String, String>> getIdAndNameMapBySupplierHisId(@PathVariable(name = "supplierHisId") String supplierHisId) {
        Map<String, String> map = productCategoryService.getIdAndNameMapBySupplierHisId(supplierHisId);
        return Result.of(map);
    }

    @Override
    public List<ProductCategoryTO> getIdAndNameMapBySupplierHisId(@RequestBody List<String> hisIdList) {
        return productCategoryService.getLatestHisListByHisId(ProductCategoryTO.class, hisIdList);
    }

    @Override
    public ProductCategoryTO getLatestHisTOByHisId(@PathVariable String hisId) {
        return productCategoryService.getLatestHisByHisId(ProductCategoryTO.class, hisId);
    }

}