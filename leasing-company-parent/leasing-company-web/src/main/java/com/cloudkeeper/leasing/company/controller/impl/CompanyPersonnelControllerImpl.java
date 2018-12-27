package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.CompanyPersonnelController;
import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelSearchable;
import com.cloudkeeper.leasing.company.service.CompanyPersonnelService;
import com.cloudkeeper.leasing.company.vo.CompanyPersonnelVO;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 公司相关人员 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyPersonnelControllerImpl implements CompanyPersonnelController {

    /** 公司相关人员 service */
    private final CompanyPersonnelService companyPersonnelService;

    @Override
    public Result<CompanyPersonnelVO> findOne(@ApiParam(value = "公司相关人员id", required = true) @PathVariable String id) {
        Optional<CompanyPersonnel> companyPersonnelOptional = companyPersonnelService.findOptionalById(id);
        return companyPersonnelOptional.map(companyPersonnel -> Result.of(companyPersonnel.convert(CompanyPersonnelVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<CompanyPersonnelVO> add(@ApiParam(value = "公司相关人员 DTO", required = true) @RequestBody @Validated CompanyPersonnelDTO companyPersonnelDTO) {
        CompanyPersonnel companyPersonnel = companyPersonnelService.save(companyPersonnelDTO.convert(CompanyPersonnel.class));
        return Result.ofAddSuccess(companyPersonnel.convert(CompanyPersonnelVO.class));
    }

    @Override
    public Result<CompanyPersonnelVO> update(@ApiParam(value = "公司相关人员id", required = true) @PathVariable String id,
        @ApiParam(value = "公司相关人员 DTO", required = true) @RequestBody @Validated CompanyPersonnelDTO companyPersonnelDTO) {
        Optional<CompanyPersonnel> companyPersonnelOptional = companyPersonnelService.findOptionalById(id);
        if (!companyPersonnelOptional.isPresent()) {
            return Result.ofLost();
        }
        CompanyPersonnel companyPersonnel = companyPersonnelOptional.get();
        BeanUtils.copyProperties(companyPersonnelDTO, companyPersonnel);
        companyPersonnel = companyPersonnelService.save(companyPersonnel);
        return Result.ofUpdateSuccess(companyPersonnel.convert(CompanyPersonnelVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "公司相关人员id", required = true) @PathVariable String id) {
        companyPersonnelService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<CompanyPersonnelVO>> list(@ApiParam(value = "公司相关人员查询条件", required = true) @RequestBody CompanyPersonnelSearchable companyPersonnelSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<CompanyPersonnel> companyPersonnelList = companyPersonnelService.findAll(companyPersonnelSearchable, sort);
        List<CompanyPersonnelVO> companyPersonnelVOList = CompanyPersonnel.convert(companyPersonnelList, CompanyPersonnelVO.class);
        return Result.of(companyPersonnelVOList);
    }

    @Override
    public Result<Page<CompanyPersonnelVO>> page(@ApiParam(value = "公司相关人员查询条件", required = true) @RequestBody CompanyPersonnelSearchable companyPersonnelSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<CompanyPersonnel> companyPersonnelPage = companyPersonnelService.findAll(companyPersonnelSearchable, pageable);
        Page<CompanyPersonnelVO> companyPersonnelVOPage = CompanyPersonnel.convert(companyPersonnelPage, CompanyPersonnelVO.class);
        return Result.of(companyPersonnelVOPage);
    }

}