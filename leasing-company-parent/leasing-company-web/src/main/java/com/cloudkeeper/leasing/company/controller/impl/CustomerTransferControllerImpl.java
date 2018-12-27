package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.CustomerTransferController;
import com.cloudkeeper.leasing.company.domain.CustomerTransfer;
import com.cloudkeeper.leasing.company.dto.customertransfer.CustomerTransferDTO;
import com.cloudkeeper.leasing.company.dto.customertransfer.CustomerTransferSearchable;
import com.cloudkeeper.leasing.company.service.CustomerTransferService;
import com.cloudkeeper.leasing.company.vo.CustomerTransferVO;
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
 * 转移客户 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerTransferControllerImpl implements CustomerTransferController {

    /** 转移客户 service */
    private final CustomerTransferService customerTransferService;

    @Override
    public Result<CustomerTransferVO> findOne(@ApiParam(value = "转移d的客户id", required = true) @PathVariable String id) {
        Optional<CustomerTransfer> customerTransferOptional = customerTransferService.findOptionalById(id);
        return customerTransferOptional.map(customerTransfer -> Result.of(customerTransfer.convert(CustomerTransferVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<CustomerTransferVO> add(@ApiParam(value = "转移d的客户 DTO", required = true) @RequestBody @Validated CustomerTransferDTO customerTransferDTO) {
        CustomerTransfer customerTransfer = customerTransferService.save(customerTransferDTO.convert(CustomerTransfer.class));
        return Result.ofAddSuccess(customerTransfer.convert(CustomerTransferVO.class));
    }

    @Override
    public Result<CustomerTransferVO> update(@ApiParam(value = "转移d的客户id", required = true) @PathVariable String id,
        @ApiParam(value = "转移d的客户 DTO", required = true) @RequestBody @Validated CustomerTransferDTO customerTransferDTO) {
        Optional<CustomerTransfer> customerTransferOptional = customerTransferService.findOptionalById(id);
        if (!customerTransferOptional.isPresent()) {
            return Result.ofLost();
        }
        CustomerTransfer customerTransfer = customerTransferOptional.get();
        BeanUtils.copyProperties(customerTransferDTO, customerTransfer);
        customerTransfer = customerTransferService.save(customerTransfer);
        return Result.ofUpdateSuccess(customerTransfer.convert(CustomerTransferVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "转移d的客户id", required = true) @PathVariable String id) {
        customerTransferService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<CustomerTransferVO>> list(@ApiParam(value = "转移d的客户查询条件", required = true) @RequestBody CustomerTransferSearchable customerTransferSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<CustomerTransfer> customerTransferList = customerTransferService.findAll(customerTransferSearchable, sort);
        List<CustomerTransferVO> customerTransferVOList = CustomerTransfer.convert(customerTransferList, CustomerTransferVO.class);
        return Result.of(customerTransferVOList);
    }

    @Override
    public Result<Page<CustomerTransferVO>> page(@ApiParam(value = "转移d的客户查询条件", required = true) @RequestBody CustomerTransferSearchable customerTransferSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<CustomerTransfer> customerTransferPage = customerTransferService.findAll(customerTransferSearchable, pageable);
        Page<CustomerTransferVO> customerTransferVOPage = CustomerTransfer.convert(customerTransferPage, CustomerTransferVO.class);
        return Result.of(customerTransferVOPage);
    }

}