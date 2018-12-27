package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.IdentificationNumberController;
import com.cloudkeeper.leasing.company.domain.IdentificationNumber;
import com.cloudkeeper.leasing.company.dto.identificationnumber.IdentificationNumberDTO;
import com.cloudkeeper.leasing.company.dto.identificationnumber.IdentificationNumberSearchable;
import com.cloudkeeper.leasing.company.service.IdentificationNumberService;
import com.cloudkeeper.leasing.company.vo.IdentificationNumberVO;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 机器编号 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IdentificationNumberControllerImpl implements IdentificationNumberController {

    /** 机器编号 service */
    private final IdentificationNumberService identificationNumberService;

    @Override
    public Result<IdentificationNumberVO> findOne(@ApiParam(value = "机器编号id", required = true) @PathVariable String id) {
        Optional<IdentificationNumber> identificationNumberOptional = identificationNumberService.findOptionalById(id);
        return identificationNumberOptional.map(identificationNumber -> Result.of(identificationNumber.convert(IdentificationNumberVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<IdentificationNumberVO> add(@ApiParam(value = "机器编号 DTO", required = true) @RequestBody @Validated IdentificationNumberDTO identificationNumberDTO) {
        IdentificationNumber identificationNumber = identificationNumberService.save(identificationNumberDTO.convert(IdentificationNumber.class));
        return Result.ofAddSuccess(identificationNumber.convert(IdentificationNumberVO.class));
    }

    @Override
    public Result<IdentificationNumberVO> update(@ApiParam(value = "机器编号id", required = true) @PathVariable String id,
        @ApiParam(value = "机器编号 DTO", required = true) @RequestBody @Validated IdentificationNumberDTO identificationNumberDTO) {
        Optional<IdentificationNumber> identificationNumberOptional = identificationNumberService.findOptionalById(id);
        if (!identificationNumberOptional.isPresent()) {
            return Result.ofLost();
        }
        IdentificationNumber identificationNumber = identificationNumberOptional.get();
        BeanUtils.copyProperties(identificationNumberDTO, identificationNumber);
        identificationNumber = identificationNumberService.save(identificationNumber);
        return Result.ofUpdateSuccess(identificationNumber.convert(IdentificationNumberVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "机器编号id", required = true) @PathVariable String id) {
        identificationNumberService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<IdentificationNumberVO>> list(@ApiParam(value = "机器编号查询条件", required = true) @RequestBody IdentificationNumberSearchable identificationNumberSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<IdentificationNumber> identificationNumberList = identificationNumberService.findAll(identificationNumberSearchable, sort);
        List<IdentificationNumberVO> identificationNumberVOList = identificationNumberList.stream().map(IdentificationNumber::convert).collect(Collectors.toList());
        return Result.of(identificationNumberVOList);
    }

    @Override
    public Result<Page<IdentificationNumberVO>> page(@ApiParam(value = "机器编号查询条件", required = true) @RequestBody IdentificationNumberSearchable identificationNumberSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<IdentificationNumber> identificationNumberPage = identificationNumberService.findAll(identificationNumberSearchable, pageable);
        List<IdentificationNumberVO> collect = identificationNumberPage.stream().map(IdentificationNumber::convert).collect(Collectors.toList());
        Page<IdentificationNumberVO> identificationNumberVOPage = new PageImpl<>(collect, pageable, identificationNumberPage.getTotalElements());
        return Result.of(identificationNumberVOPage);
    }

}