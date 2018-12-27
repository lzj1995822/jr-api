package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.AssociationRelationshipController;
import com.cloudkeeper.leasing.company.domain.AssociationRelationship;
import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipDTO;
import com.cloudkeeper.leasing.company.dto.associationrelationship.AssociationRelationshipSearchable;
import com.cloudkeeper.leasing.company.service.AssociationRelationshipService;
import com.cloudkeeper.leasing.company.vo.AssociationRelationshipVO;
import com.querydsl.core.BooleanBuilder;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 关联关系 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationRelationshipControllerImpl implements AssociationRelationshipController {

    /** 关联关系 service */
    private final AssociationRelationshipService associationRelationshipService;

    @Override
    public Result<AssociationRelationshipVO> findOne(@ApiParam(value = "关联关系id", required = true) @PathVariable String id) {
        Optional<AssociationRelationship> associationRelationshipOptional = associationRelationshipService.findOptionalById(id);
        return associationRelationshipOptional.map(associationRelationship -> Result.of(associationRelationship.convert(AssociationRelationshipVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<AssociationRelationshipVO> add(@ApiParam(value = "关联关系 DTO", required = true) @RequestBody @Validated AssociationRelationshipDTO associationRelationshipDTO) {
        AssociationRelationship associationRelationship = associationRelationshipService.save(associationRelationshipDTO.convert(AssociationRelationship.class));
        return Result.ofAddSuccess(associationRelationship.convert(AssociationRelationshipVO.class));
    }

    @Override
    public Result<AssociationRelationshipVO> update(@ApiParam(value = "关联关系id", required = true) @PathVariable String id,
        @ApiParam(value = "关联关系 DTO", required = true) @RequestBody @Validated AssociationRelationshipDTO associationRelationshipDTO) {
        Optional<AssociationRelationship> associationRelationshipOptional = associationRelationshipService.findOptionalById(id);
        if (!associationRelationshipOptional.isPresent()) {
            return Result.ofLost();
        }
        AssociationRelationship associationRelationship = associationRelationshipOptional.get();
        BeanUtils.copyProperties(associationRelationshipDTO, associationRelationship);
        associationRelationship = associationRelationshipService.save(associationRelationship);
        return Result.ofUpdateSuccess(associationRelationship.convert(AssociationRelationshipVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "关联关系id", required = true) @PathVariable String id) {
        associationRelationshipService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<AssociationRelationshipVO>> list(@ApiParam(value = "关联关系查询条件", required = true) @RequestBody AssociationRelationshipSearchable associationRelationshipSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<AssociationRelationship> associationRelationshipList = associationRelationshipService.findAll(associationRelationshipSearchable, sort);
        List<AssociationRelationshipVO> associationRelationshipVOList = associationRelationshipList.stream().map(AssociationRelationship::convert).collect(Collectors.toList());
        return Result.of(associationRelationshipVOList);
    }

    @Override
    public Result<Page<AssociationRelationshipVO>> page(@ApiParam(value = "关联关系查询条件", required = true) @RequestBody AssociationRelationshipSearchable associationRelationshipSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        BooleanBuilder booleanBuilder = associationRelationshipService.getBooleanBuilder(associationRelationshipSearchable);
        Page<AssociationRelationship> associationRelationshipPage = associationRelationshipService.findAll(booleanBuilder, pageable);
        List<AssociationRelationshipVO> content = associationRelationshipPage.getContent().stream().map(AssociationRelationship::convert).collect(Collectors.toList());
        Page<AssociationRelationshipVO> associationRelationshipVOPage = new PageImpl<>(content, pageable, associationRelationshipPage.getTotalElements());
        return Result.of(associationRelationshipVOPage);
    }

}