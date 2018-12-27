package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.company.controller.VisitPlanController;
import com.cloudkeeper.leasing.company.domain.VisitPlan;
import com.cloudkeeper.leasing.company.dto.visitPlan.VisitPlanDTO;
import com.cloudkeeper.leasing.company.dto.visitPlan.VisitPlanSearchable;
import com.cloudkeeper.leasing.company.service.VisitPlanService;
import com.cloudkeeper.leasing.company.vo.VisitPlanVO;
import com.querydsl.core.BooleanBuilder;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class VisitPlanControllerImpl implements VisitPlanController {

    /** 拜访计划Service*/
    @Autowired
    private VisitPlanService visitPlanService;

    @Override
    public Result<VisitPlanVO> add(@ApiParam(value = "拜访计划dto") @RequestBody @Validated VisitPlanDTO visitPlanDTO) {
        VisitPlan visitPlan = visitPlanService.save(visitPlanDTO.convert(VisitPlan.class));
        return Result.ofAddSuccess(visitPlan.convert(VisitPlanVO.class));
    }

    @Override
    public Result<VisitPlanVO> update(@ApiParam(value = "更新id") @PathVariable String id, @ApiParam(value = "拜访计划dto") @RequestBody @Validated VisitPlanDTO visitPlanDTO) {
        Optional<VisitPlan> visitPlanOptional = visitPlanService.findOptionalById(id);
        if (!visitPlanOptional.isPresent()) {
            return Result.of(Result.ResultCode.FAIL.getCode(), "修改的数据不存在，请刷新后重试！");
        }
        VisitPlan visitPlan = visitPlanOptional.get();
        BeanUtils.copyProperties(visitPlanDTO, visitPlan);
        visitPlan = visitPlanService.save(visitPlan);
        return Result.ofUpdateSuccess(visitPlan.convert(VisitPlanVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "删除id") @PathVariable String id) {
        visitPlanService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<VisitPlanVO> findOne(@ApiParam(value = "id") @PathVariable String id) {
        Optional<VisitPlan> visitPlanOptional = visitPlanService.findOptionalById(id);
        return visitPlanOptional.map(visitPlan -> Result.of(visitPlan.convert(VisitPlanVO.class))).orElseGet(() -> Result.of("车辆不存在"));
    }

    @Override
    public Result<List<VisitPlanVO>> list(@ApiParam(value = "出勤查询条件", required = true) @RequestBody VisitPlanSearchable visitPlanSearchable,
                                          @ApiParam(value = "排序条件") Sort sort) {
        BooleanBuilder booleanBuilder = visitPlanService.defaultBooleanBuilder(visitPlanSearchable);
        Iterable<VisitPlan> visitPlans = visitPlanService.findAll(booleanBuilder, sort);
        List<VisitPlan> convert = VisitPlan.convert(visitPlans, VisitPlan.class);
        List<VisitPlanVO> visitPlanVOList = visitPlanService.convertToVOs(convert);
        return Result.of(visitPlanVOList);
    }

    @Override
    public Result<Page<VisitPlanVO>> page(@ApiParam(value = "出勤查询条件", required = true) @RequestBody VisitPlanSearchable visitPlanSearchable,
                                          @ApiParam(value = "分页条件") Pageable pageable) {
        BooleanBuilder booleanBuilder = visitPlanService.defaultBooleanBuilder(visitPlanSearchable);
        Page<VisitPlan> visitPlanPage = visitPlanService.findAll(booleanBuilder, pageable);
        Page<VisitPlanVO> visitPlanVOPage = new RestPageImpl<>(visitPlanService.convertToVOs(visitPlanPage.getContent()), pageable,visitPlanPage.getTotalElements());
        return Result.of(visitPlanVOPage);
    }
}


