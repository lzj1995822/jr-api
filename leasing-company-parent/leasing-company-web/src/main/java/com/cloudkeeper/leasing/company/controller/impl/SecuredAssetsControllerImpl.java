package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.SecuredAssetsController;
import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsSearchable;
import com.cloudkeeper.leasing.company.service.SecuredAssetsService;
import com.cloudkeeper.leasing.company.vo.SecuredAssetsVO;
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
 * 担保资产 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecuredAssetsControllerImpl implements SecuredAssetsController {

    /** 担保资产 service */
    private final SecuredAssetsService securedAssetsService;

    @Override
    public Result<SecuredAssetsVO> findOne(@ApiParam(value = "担保资产id", required = true) @PathVariable String id) {
        Optional<SecuredAssets> securedAssetsOptional = securedAssetsService.findOptionalById(id);
        return securedAssetsOptional.map(securedAssets -> Result.of(securedAssets.convert(SecuredAssetsVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<SecuredAssetsVO> add(@ApiParam(value = "担保资产 DTO", required = true) @RequestBody @Validated SecuredAssetsDTO securedAssetsDTO) {
        SecuredAssets securedAssets = securedAssetsService.save(securedAssetsDTO.convert(SecuredAssets.class));
        return Result.ofAddSuccess(securedAssets.convert(SecuredAssetsVO.class));
    }

    @Override
    public Result<SecuredAssetsVO> update(@ApiParam(value = "担保资产id", required = true) @PathVariable String id,
        @ApiParam(value = "担保资产 DTO", required = true) @RequestBody @Validated SecuredAssetsDTO securedAssetsDTO) {
        Optional<SecuredAssets> securedAssetsOptional = securedAssetsService.findOptionalById(id);
        if (!securedAssetsOptional.isPresent()) {
            return Result.ofLost();
        }
        SecuredAssets securedAssets = securedAssetsOptional.get();
        BeanUtils.copyProperties(securedAssetsDTO, securedAssets);
        securedAssets = securedAssetsService.save(securedAssets);
        return Result.ofUpdateSuccess(securedAssets.convert(SecuredAssetsVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "担保资产id", required = true) @PathVariable String id) {
        securedAssetsService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<SecuredAssetsVO>> list(@ApiParam(value = "担保资产查询条件", required = true) @RequestBody SecuredAssetsSearchable securedAssetsSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<SecuredAssets> securedAssetsList = securedAssetsService.findAll(securedAssetsSearchable, sort);
        List<SecuredAssetsVO> securedAssetsVOList = SecuredAssets.convert(securedAssetsList, SecuredAssetsVO.class);
        return Result.of(securedAssetsVOList);
    }

    @Override
    public Result<Page<SecuredAssetsVO>> page(@ApiParam(value = "担保资产查询条件", required = true) @RequestBody SecuredAssetsSearchable securedAssetsSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<SecuredAssets> securedAssetsPage = securedAssetsService.findAll(securedAssetsSearchable, pageable);
        Page<SecuredAssetsVO> securedAssetsVOPage = SecuredAssets.convert(securedAssetsPage, SecuredAssetsVO.class);
        return Result.of(securedAssetsVOPage);
    }

}