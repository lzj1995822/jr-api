package com.cloudkeeper.leasing.company.controller;

import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferSearchable;
import com.cloudkeeper.leasing.company.vo.BusinessTransferVO;
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

/**
 * 业务员转移、转移客户主记录 controller
 * @author asher
 */
@Api(value = "业务员转移、转移客户主记录", tags = "业务员转移、转移客户主记录")
@RequestMapping("/businessTransfer")
public interface BusinessTransferController {

    /**
     * 查询
     * @param id 业务员转移、转移客户主记录id
     * @return 业务员转移、转移客户主记录 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<BusinessTransferVO> findOne(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param businessTransferDTO 业务员转移、转移客户主记录 DTO
     * @return 业务员转移、转移客户主记录 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<BusinessTransferVO> add(@ApiParam(value = "业务员转移、转移客户主记录 DTO", required = true) @RequestBody @Validated BusinessTransferDTO businessTransferDTO);

    /**
     * 更新
     * @param id 业务员转移、转移客户主记录id
     * @param businessTransferDTO 业务员转移、转移客户主记录 DTO
     * @return 业务员转移、转移客户主记录 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<BusinessTransferVO> update(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id,
        @ApiParam(value = "业务员转移、转移客户主记录 DTO", required = true) @RequestBody @Validated BusinessTransferDTO businessTransferDTO);

    /**
     * 删除
     * @param id 业务员转移、转移客户主记录id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param businessTransferSearchable 业务员转移、转移客户主记录查询条件
     * @param sort 排序条件
     * @return 业务员转移、转移客户主记录 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 5)
    @PostMapping("/list")
    Result<List<BusinessTransferVO>> list(@ApiParam(value = "业务员转移、转移客户主记录查询条件", required = true) @RequestBody BusinessTransferSearchable businessTransferSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param businessTransferSearchable 业务员转移、转移客户主记录查询条件
     * @param pageable 分页条件
     * @return 业务员转移、转移客户主记录 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<BusinessTransferVO>> page(@ApiParam(value = "业务员转移、转移客户主记录查询条件", required = true) @RequestBody BusinessTransferSearchable businessTransferSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable);

}