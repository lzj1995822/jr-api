package com.cloudkeeper.leasing.company.controller;

import com.cloudkeeper.leasing.bean.company.to.ProductCategoryTO;
import com.cloudkeeper.leasing.company.domain.ProductCategory;
import com.cloudkeeper.leasing.company.dto.productcategory.ProductCategoryDTO;
import com.cloudkeeper.leasing.company.dto.productcategory.ProductCategorySearchable;
import com.cloudkeeper.leasing.company.vo.ProductCategoryVO;
import com.cloudkeeper.leasing.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 产品分类 controller
 * @author asher
 */
@Api(value = "产品分类", tags = "产品分类")
@RequestMapping("/productCategory")
public interface ProductCategoryController {

    /**
     * 查询
     * @param id 产品分类id
     * @return 产品分类 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<ProductCategoryVO> findOne(@ApiParam(value = "产品分类id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param productCategoryDTO 产品分类 DTO
     * @return 产品分类 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<ProductCategoryVO> add(@ApiParam(value = "产品分类 DTO", required = true) @RequestBody @Validated ProductCategoryDTO productCategoryDTO);

    /**
     * 更新
     * @param id 产品分类id
     * @param productCategoryDTO 产品分类 DTO
     * @return 产品分类 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<ProductCategoryVO> update(@ApiParam(value = "产品分类id", required = true) @PathVariable String id,
        @ApiParam(value = "产品分类 DTO", required = true) @RequestBody @Validated ProductCategoryDTO productCategoryDTO);

    /**
     * 删除
     * @param id 产品分类id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "产品分类id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param productCategorySearchable 产品分类查询条件
     * @param sort 排序条件
     * @return 产品分类 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 5)
    @PostMapping("/list")
    Result<List<ProductCategoryVO>> list(@ApiParam(value = "产品分类查询条件", required = true) @RequestBody ProductCategorySearchable productCategorySearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param productCategorySearchable 产品分类查询条件
     * @param pageable 分页条件
     * @return 产品分类 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<ProductCategoryVO>> page(@ApiParam(value = "产品分类查询条件", required = true) @RequestBody ProductCategorySearchable productCategorySearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable);

    @ApiOperation(value = "根据供应商查产品分类", notes = "根据供应商查产品分类", position = 10)
    @GetMapping("/map/{supplierHisId}supplierHisId")
    Result<Map<String, String>> getIdAndNameMapBySupplierHisId( String supplierHisId);

    @ApiOperation(value = "根据分类idlist查最新历史记录集合", notes = "根据分类idlist查最新历史记录集合", position = 12)
    @PostMapping("/to/latestHisList")
    List<ProductCategoryTO> getIdAndNameMapBySupplierHisId(@RequestBody List<String> hisIdList);

    @ApiOperation(value = "根据历史记录id获取最新的历史记录", notes = "根据历史记录id获取最新的历史记录", position = 14)
    @GetMapping("/to/latestHis/{hisId}hisId")
    ProductCategoryTO getLatestHisTOByHisId(@ApiParam(value = "型号历史id", required = true) @PathVariable String hisId);
}