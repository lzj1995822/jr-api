package com.cloudkeeper.leasing.company.controller;

import com.cloudkeeper.leasing.bean.company.to.SupplierAllTO;
import com.cloudkeeper.leasing.bean.company.to.SupplierTO;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierDTO;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierSearchable;
import com.cloudkeeper.leasing.company.vo.SupplierAllVO;
import com.cloudkeeper.leasing.company.vo.SupplierVO;
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
 * 供应商 controller
 * @author asher
 */
@Api(value = "供应商", tags = "供应商")
@RequestMapping("/supplier")
public interface SupplierController {

    /**
     * 查询
     * @param id 供应商id
     * @return 供应商 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<SupplierVO> findOne(@ApiParam(value = "供应商id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param supplierDTO 供应商 DTO
     * @return 供应商 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<SupplierVO> add(@ApiParam(value = "供应商 DTO", required = true) @RequestBody @Validated SupplierDTO supplierDTO);

    /**
     * 更新
     * @param id 供应商id
     * @param supplierDTO 供应商 DTO
     * @return 供应商 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<SupplierVO> update(@ApiParam(value = "供应商id", required = true) @PathVariable String id,
        @ApiParam(value = "供应商 DTO", required = true) @RequestBody @Validated SupplierDTO supplierDTO);

    /**
     * 删除
     * @param id 供应商id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "供应商id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param supplierSearchable 供应商查询条件
     * @param sort 排序条件
     * @return 供应商 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 5)
    @PostMapping("/list")
    Result<List<SupplierAllVO>> list(@ApiParam(value = "供应商查询条件", required = true) @RequestBody SupplierSearchable supplierSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param supplierSearchable 供应商查询条件
     * @param pageable 分页条件
     * @return 供应商 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<SupplierAllVO>> page(@ApiParam(value = "供应商查询条件", required = true) @RequestBody SupplierSearchable supplierSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable);


    @ApiOperation(value = "获取历史记录", notes = "获取历史记录", position = 8)
    @GetMapping("/his/{hisId}hisId")
    Result<SupplierAllVO> getHis(@ApiParam(value = "客户历史id", required = true) @PathVariable String hisId);

    @ApiOperation(value = "获取历史记录", notes = "获取历史记录", position = 10)
    @GetMapping("/his/{businessId}businessId")
    Result<SupplierAllVO> getHisByBusinessId(@ApiParam(value = "客户历史id", required = true) @PathVariable String businessId);

    @ApiOperation(value = "获取历史记录", notes = "获取历史记录", position = 12)
    @GetMapping("/to/his/{hisId}hisId")
    SupplierAllTO getHisTO(String hisId);

    @ApiOperation(value = "获取历史记录", notes = "获取历史记录", position = 14)
    @GetMapping("/to/his/{businessId}businessId")
    SupplierAllTO getHisTOByBusinessId(String businessId);

    @ApiOperation(value = "获取id-name下拉集合", notes = "获取id-name下拉集合", position = 16)
    @GetMapping("/map")
    Result<Map<String, String>> getIdAndName();

    @ApiOperation(value = "根据历史记录id获取最新历史记录", notes = "根据历史记录id获取最新历史记录", position = 18)
    @GetMapping("/to/latestHis/{hisId}hisId")
    SupplierAllTO getLatestHisTOByHisId(@ApiParam(value = "客户历史id", required = true) @PathVariable String hisId);

    @ApiOperation(value = "根据历史记录id集合获取最新历史记录集合", notes = "根据历史记录id集合获取最新历史记录集合", position = 18)
    @PostMapping("/to/latestHisList/")
    List<SupplierTO> getLatestIdAndNameMap(@RequestBody List<String> hisIdList);
}