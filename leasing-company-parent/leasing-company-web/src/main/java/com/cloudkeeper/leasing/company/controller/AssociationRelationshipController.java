package com.cloudkeeper.leasing.company.controller;

import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipDTO;
import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipSearchable;
import com.cloudkeeper.leasing.company.vo.AssociationRelationshipVO;
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
 * 关联关系 controller
 * @author asher
 */
@Api(value = "关联关系", tags = "关联关系")
@RequestMapping("/associationRelationship")
public interface AssociationRelationshipController {

    /**
     * 查询
     * @param id 关联关系id
     * @return 关联关系 VO
     */
    @ApiOperation(value = "查询", notes = "查询", position = 1)
    @GetMapping("/{id}id")
    Result<AssociationRelationshipVO> findOne(@ApiParam(value = "关联关系id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param associationRelationshipDTO 关联关系 DTO
     * @return 关联关系 VO
     */
    @ApiOperation(value = "新增", notes = "新增", position = 2)
    @PostMapping("/")
    Result<AssociationRelationshipVO> add(@ApiParam(value = "关联关系 DTO", required = true) @RequestBody @Validated AssociationRelationshipDTO associationRelationshipDTO);

    /**
     * 更新
     * @param id 关联关系id
     * @param associationRelationshipDTO 关联关系 DTO
     * @return 关联关系 VO
     */
    @ApiOperation(value = "更新", notes = "更新", position = 3)
    @PutMapping("/{id}id")
    Result<AssociationRelationshipVO> update(@ApiParam(value = "关联关系id", required = true) @PathVariable String id,
        @ApiParam(value = "关联关系 DTO", required = true) @RequestBody @Validated AssociationRelationshipDTO associationRelationshipDTO);

    /**
     * 删除
     * @param id 关联关系id
     * @return 删除结果
     */
    @ApiOperation(value = "删除", notes = "删除", position = 4)
    @DeleteMapping("/{id}id")
    Result delete(@ApiParam(value = "关联关系id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param associationRelationshipSearchable 关联关系查询条件
     * @param sort 排序条件
     * @return 关联关系 VO 集合
     */
    @ApiOperation(value = "列表查询", notes = "列表查询<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 5)
    @PostMapping("/list")
    Result<List<AssociationRelationshipVO>> list(@ApiParam(value = "关联关系查询条件", required = true) @RequestBody AssociationRelationshipSearchable associationRelationshipSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort);

    /**
     * 分页查询
     * @param associationRelationshipSearchable 关联关系查询条件
     * @param pageable 分页条件
     * @return 关联关系 VO 分页
     */
    @ApiOperation(value = "分页查询", notes = "分页查询<br/>page：第几页，默认为0，是第一页<br/>size：分页大小, 默认为10<br/>sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc", position = 6)
    @PostMapping("/page")
    Result<Page<AssociationRelationshipVO>> page(@ApiParam(value = "关联关系查询条件", required = true) @RequestBody AssociationRelationshipSearchable associationRelationshipSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable);

}