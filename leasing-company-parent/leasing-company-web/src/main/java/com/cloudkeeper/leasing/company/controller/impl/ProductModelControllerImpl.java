package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.ProductModelTO;
import com.cloudkeeper.leasing.company.controller.ProductModelController;
import com.cloudkeeper.leasing.company.domain.ProductModel;
import com.cloudkeeper.leasing.company.dto.productmodel.ProductModelDTO;
import com.cloudkeeper.leasing.company.dto.productmodel.ProductModelSearchable;
import com.cloudkeeper.leasing.company.service.ProductModelService;
import com.cloudkeeper.leasing.company.vo.ProductModelVO;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 产品型号 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductModelControllerImpl implements ProductModelController {

    /** 产品型号 service */
    private final ProductModelService productModelService;

    @Override
    public Result<ProductModelVO> findOne(@ApiParam(value = "产品型号id", required = true) @PathVariable String id) {
        Optional<ProductModel> productModelOptional = productModelService.findOptionalById(id);
        return productModelOptional.map(productModel -> Result.of(productModel.convert(ProductModelVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<ProductModelVO> add(@ApiParam(value = "产品型号 DTO", required = true) @RequestBody @Validated ProductModelDTO productModelDTO) {
        ProductModel productModel = productModelService.save(productModelDTO.convert(ProductModel.class));
        return Result.ofAddSuccess(productModel.convert(ProductModelVO.class));
    }

    @Override
    public Result<ProductModelVO> update(@ApiParam(value = "产品型号id", required = true) @PathVariable String id,
        @ApiParam(value = "产品型号 DTO", required = true) @RequestBody @Validated ProductModelDTO productModelDTO) {
        Optional<ProductModel> productModelOptional = productModelService.findOptionalById(id);
        if (!productModelOptional.isPresent()) {
            return Result.ofLost();
        }
        ProductModel productModel = productModelOptional.get();
        BeanUtils.copyProperties(productModelDTO, productModel);
        productModel = productModelService.save(productModel);
        return Result.ofUpdateSuccess(productModel.convert(ProductModelVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "产品型号id", required = true) @PathVariable String id) {
        productModelService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<ProductModelVO>> list(@ApiParam(value = "产品型号查询条件", required = true) @RequestBody ProductModelSearchable productModelSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<ProductModel> productModelList = productModelService.findAll(productModelSearchable, sort);
        List<ProductModelVO> productModelVOList = productModelList.stream().map(ProductModel::convert).collect(Collectors.toList());
        return Result.of(productModelVOList);
    }

    @Override
    public Result<Page<ProductModelVO>> page(@ApiParam(value = "产品型号查询条件", required = true) @RequestBody ProductModelSearchable productModelSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<ProductModel> productModelPage = productModelService.findAll(productModelSearchable, pageable);
        List<ProductModelVO> collect = productModelPage.stream().map(ProductModel::convert).collect(Collectors.toList());
        Page<ProductModelVO> productModelVOPage = new PageImpl<>(collect, pageable, productModelPage.getTotalElements());
        return Result.of(productModelVOPage);
    }

    @Override
    public Result<Map<String, String>> getIdAndNameMapByCategoryHisId(@PathVariable(name = "categoryHisId") String categoryHisId) {
        Map<String, String> map = productModelService.getIdAndNameMapByCategoryHisId(categoryHisId);
        return Result.of(map);
    }

    @Override
    public Result<ProductModelVO> getHisVO(@PathVariable(value = "hisId") String hisId) {
        ProductModelVO his = productModelService.getHis(ProductModelVO.class, hisId);
        if (his == null) {
            Result.ofNotFound();
        }
        return Result.of(his);
    }

    @Override
    public ProductModelTO getHisTO(@PathVariable(value = "hisId") String hisId) {
        return productModelService.getHis(ProductModelTO.class, hisId);
    }

    @Override
    public ProductModelTO getLatestHisTOByHisId(@PathVariable(value = "hisId") String hisId) {
        return productModelService.getLatestHisByHisId(ProductModelTO.class, hisId);
    }

    @Override
    public List<ProductModelTO> getIdAndNameMapBySupplierHisId(@RequestBody List<String> hisIdList) {
        return productModelService.getLatestHisListByHisId(ProductModelTO.class, hisIdList);
    }

}