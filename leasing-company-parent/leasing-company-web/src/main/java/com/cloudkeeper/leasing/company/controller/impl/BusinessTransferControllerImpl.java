package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.company.controller.BusinessTransferController;
import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferSearchable;
import com.cloudkeeper.leasing.company.service.BusinessTransferService;
import com.cloudkeeper.leasing.company.vo.BusinessTransferVO;
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
 * 业务员转移、转移客户主记录 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessTransferControllerImpl implements BusinessTransferController {

    /** 业务员转移、转移客户主记录 service */
    private final BusinessTransferService businessTransferService;

    @Override
    public Result<BusinessTransferVO> findOne(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id) {
        Optional<BusinessTransfer> businessTransferOptional = businessTransferService.findOptionalById(id);
        return businessTransferOptional.map(businessTransfer -> Result.of(businessTransfer.convert())).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<BusinessTransferVO> add(@ApiParam(value = "业务员转移、转移客户主记录 DTO", required = true) @RequestBody @Validated BusinessTransferDTO businessTransferDTO) {
        BusinessTransfer businessTransfer = businessTransferService.save(businessTransferDTO);
        return Result.ofAddSuccess(businessTransfer.convert());
    }

    @Override
    public Result<BusinessTransferVO> update(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id,
        @ApiParam(value = "业务员转移、转移客户主记录 DTO", required = true) @RequestBody @Validated BusinessTransferDTO businessTransferDTO) {
        Optional<BusinessTransfer> businessTransferOptional = businessTransferService.findOptionalById(id);
        if (!businessTransferOptional.isPresent()) {
            return Result.ofLost();
        }
        BusinessTransfer businessTransfer = businessTransferOptional.get();
        BeanUtils.copyProperties(businessTransferDTO, businessTransfer);
        businessTransfer = businessTransferService.save(businessTransfer);
        return Result.ofUpdateSuccess(businessTransfer.convert(BusinessTransferVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "业务员转移、转移客户主记录id", required = true) @PathVariable String id) {
        businessTransferService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<BusinessTransferVO>> list(@ApiParam(value = "业务员转移、转移客户主记录查询条件", required = true) @RequestBody BusinessTransferSearchable businessTransferSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<BusinessTransfer> businessTransferList = businessTransferService.findAll(businessTransferSearchable, sort);
        List<BusinessTransferVO> businessTransferVOList = businessTransferService.convertToVOs(businessTransferList);
        return Result.of(businessTransferVOList);
    }

    @Override
    public Result<Page<BusinessTransferVO>> page(@ApiParam(value = "业务员转移、转移客户主记录查询条件", required = true) @RequestBody BusinessTransferSearchable businessTransferSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<BusinessTransfer> businessTransferPage = businessTransferService.findAll(businessTransferSearchable, pageable);
        List<BusinessTransferVO> businessTransferVOS = businessTransferService.convertToVOs(businessTransferPage.getContent());
        Page<BusinessTransferVO> businessTransferVOPage = new RestPageImpl<>(businessTransferVOS, businessTransferPage.getPageable(), businessTransferPage.getTotalElements());
        return Result.of(businessTransferVOPage);
    }

}