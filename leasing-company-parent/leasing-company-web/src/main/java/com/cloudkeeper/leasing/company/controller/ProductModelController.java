package com.cloudkeeper.leasing.company.controller;

import com.cloudkeeper.leasing.bean.company.to.ProductCategoryTO;
import com.cloudkeeper.leasing.bean.company.to.ProductModelTO;
import com.cloudkeeper.leasing.bean.company.to.SupplierAllTO;
import com.cloudkeeper.leasing.company.dto.productmodel.ProductModelDTO;
import com.cloudkeeper.leasing.company.dto.productmodel.ProductModelSearchable;
import com.cloudkeeper.leasing.company.vo.ProductModelVO;
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
 * 产品型号 controller
 * @author asher
 */
@Api(value = "产品型号", tags = "产品型号")
@RequestMapping("/productModel")
public interface ProductModelController {

    /**
     * 查询
     * @param id 产品型号id
     * @return 产品型号 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<ProductModelVO> findOne(@ApiParam(value = "产品型号id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param productModelDTO 产品型号 DTO
     * @return 产品型号 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<ProductModelVO> add(@ApiParam(value = "产品型号 DTO", required = true) @RequestBody @Validated ProductModelDTO productModelDTO);

    /**
     * 更新
     * @param id 产品型号id
     * @param productModelDTO 产品型号 DTO
     * @return 产品型号 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<ProductModelVO> update(@ApiParam(value = "产品型号id", required = true) @PathVariable String id,
        @ApiParam(value = "产品型号 DTO", required = true) @RequestBody @Validated ProductModelDTO productModelDTO);

    /**
     * 删除
     * @param id 产品型号id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "产品型号id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param productModelSearchable 产品型号查询条件
     * @param sort 排序条件
     * @return 产品型号 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=no" +
            "te,desc", position = 5)
    @PostMapping("/list")
    Result<List<ProductModelVO>> list(@ApiParam(value = "产品型号查询条件", required = true) @RequestBody ProductModelSearchable productModelSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param productModelSearchable 产品型号查询条件
     * @param pageable 分页条件
     * @return 产品型号 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<ProductModelVO>> page(@ApiParam(value = "产品型号查询条件", required = true) @RequestBody ProductModelSearchable productModelSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable);

    @ApiOperation(value = "根据产品分类查型号", notes = "根据产品分类查型号", position = 8)
    @GetMapping("/map/{categoryHisId}categoryHisId")
    Result<Map<String, String>> getIdAndNameMapByCategoryHisId(String categoryHisId);

    @ApiOperation(value = "获取指定历史记录", notes = "获取指定历史记录", position = 10)
    @GetMapping("/his/{hisId}hisId")
    Result<ProductModelVO> getHisVO(String hisId);

    @ApiOperation(value = "获取指定历史记录TO", notes = "获取指定历史记录TO", position = 12)
    @GetMapping("/to/his/{hisId}hisId")
    ProductModelTO getHisTO(String hisId);

    @ApiOperation(value = "根据历史记录id获取最新的历史记录", notes = "根据历史记录id获取最新的历史记录", position = 14)
    @GetMapping("/to/latestHis/{hisId}hisId")
    ProductModelTO getLatestHisTOByHisId(@ApiParam(value = "型号历史id", required = true) @PathVariable String hisId);

    @ApiOperation(value = "根据型号idlist查最新历史记录集合", notes = "根据型号idlist查最新历史记录集合", position = 16)
    @PostMapping("/to/latestHisList")
    List<ProductModelTO> getIdAndNameMapBySupplierHisId(@RequestBody List<String> hisIdList);

}