package com.cloudkeeper.leasing.company.controller;


import com.cloudkeeper.leasing.bean.company.to.GuarantorAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuarantorTO;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorDTO;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorSearchable;
import com.cloudkeeper.leasing.company.vo.GuarantorAllVO;
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
 * 担保人 controller
 * @author asher
 */
@Api(value = "担保人", tags = "担保人")
@RequestMapping("/guarantor")
public interface GuarantorController {

    /**
     * 查询
     * @param id 担保人id
     * @return 担保人 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<GuarantorAllVO> findOne(@ApiParam(value = "担保人id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param guarantorDTO 担保人 DTO
     * @return 担保人 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<GuarantorAllVO> add(@ApiParam(value = "担保人 DTO", required = true) @RequestBody @Validated GuarantorDTO guarantorDTO);

    /**
     * 更新
     * @param id 担保人id
     * @param guarantorDTO 担保人 DTO
     * @return 担保人 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<GuarantorAllVO> update(@ApiParam(value = "担保人id", required = true) @PathVariable String id,
                                  @ApiParam(value = "担保人 DTO", required = true) @RequestBody @Validated GuarantorDTO guarantorDTO);

    /**
     * 删除
     * @param id 担保人id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "担保人id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param guarantorSearchable 担保人查询条件
     * @param sort 排序条件
     * @return 担保人 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 5)
    @PostMapping("/list")
    Result<List<GuarantorAllVO>> list(@ApiParam(value = "担保人查询条件", required = true) @RequestBody GuarantorSearchable guarantorSearchable,
                                      @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param guarantorSearchable 担保人查询条件
     * @param pageable 分页条件
     * @return 担保人 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<GuarantorAllVO>> page(@ApiParam(value = "担保人查询条件", required = true) @RequestBody GuarantorSearchable guarantorSearchable,
                                      @ApiParam(value = "分页参数", required = true) Pageable pageable);

    /**
     * 查询对应担保人历史
     * @param hsId 担保人历史hsId
     * @return 担保人历史 VO
     */
    @ApiOperation(value = "查询担保人历史", notes = "查询担保人历史", position = 7)
    @GetMapping("to/history/{hsId}id")
    GuarantorAllTO findHistory(@ApiParam(value = "担保人历史id", required = true) @PathVariable String hsId);

    /**
     * 查询最新担保人历史
     * @param hsId 担保人历史hsId
     * @return 担保人历史 VO
     */
    @ApiOperation(value = "查询最新担保人历史", notes = "查询最新担保人历史", position = 8)
    @GetMapping("to/newHistory/{hsId}id")
    GuarantorAllTO findNewHistory(@ApiParam(value = "担保人历史id", required = true) @PathVariable String hsId);

    /**
     * 查询对应历史集合
     * @param ids 历史id集合
     * @return 历史集合
     */
    @ApiOperation(value = "查询担保人历史集合", notes = "查询担保人历史集合", position = 7)
    @PutMapping("to/histList")
    List<GuarantorTO> findByHistList(@ApiParam(value = "担保人历史id集合", required = true) @RequestBody  List<String> ids);

    /**
     * 查询最新历史集合
     * @param ids 历史id集合
     * @return 最新历史集合
     */
    @ApiOperation(value = "查询担保人最新历史集合", notes = "查询担保人最新历史集合", position = 7)
    @PutMapping("to/lastHistList")
    List<GuarantorTO> findByLastHistList(@ApiParam(value = "担保人历史id集合", required = true) @RequestBody  List<String> ids);

}