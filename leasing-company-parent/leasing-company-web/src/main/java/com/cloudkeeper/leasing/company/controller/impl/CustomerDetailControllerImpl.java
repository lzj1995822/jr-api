package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.CustomerDetailController;
import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailDTO;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailSearchable;
import com.cloudkeeper.leasing.company.service.CustomerDetailService;
import com.cloudkeeper.leasing.company.vo.CustomerDetailVO;
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
 * 客户详细、开票资料 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerDetailControllerImpl implements CustomerDetailController {

    /** 客户详细、开票资料 service */
    private final CustomerDetailService customerDetailService;

    @Override
    public Result<CustomerDetailVO> findOne(@ApiParam(value = "客户详细、开票资料id", required = true) @PathVariable String id) {
        Optional<CustomerDetail> customerDetailOptional = customerDetailService.findOptionalById(id);
        return customerDetailOptional.map(customerDetail -> Result.of(customerDetail.convert(CustomerDetailVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<CustomerDetailVO> add(@ApiParam(value = "客户详细、开票资料 DTO", required = true) @RequestBody @Validated CustomerDetailDTO customerDetailDTO) {
        CustomerDetail customerDetail = customerDetailService.save(customerDetailDTO.convert(CustomerDetail.class));
        return Result.ofAddSuccess(customerDetail.convert(CustomerDetailVO.class));
    }

    @Override
    public Result<CustomerDetailVO> update(@ApiParam(value = "客户详细、开票资料id", required = true) @PathVariable String id,
        @ApiParam(value = "客户详细、开票资料 DTO", required = true) @RequestBody @Validated CustomerDetailDTO customerDetailDTO) {
        Optional<CustomerDetail> customerDetailOptional = customerDetailService.findOptionalById(id);
        if (!customerDetailOptional.isPresent()) {
            return Result.ofLost();
        }
        CustomerDetail customerDetail = customerDetailOptional.get();
        BeanUtils.copyProperties(customerDetailDTO, customerDetail);
        customerDetail = customerDetailService.save(customerDetail);
        return Result.ofUpdateSuccess(customerDetail.convert(CustomerDetailVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "客户详细、开票资料id", required = true) @PathVariable String id) {
        customerDetailService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<CustomerDetailVO>> list(@ApiParam(value = "客户详细、开票资料查询条件", required = true) @RequestBody CustomerDetailSearchable customerDetailSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<CustomerDetail> customerDetailList = customerDetailService.findAll(customerDetailSearchable, sort);
        List<CustomerDetailVO> customerDetailVOList = CustomerDetail.convert(customerDetailList, CustomerDetailVO.class);
        return Result.of(customerDetailVOList);
    }

    @Override
    public Result<Page<CustomerDetailVO>> page(@ApiParam(value = "客户详细、开票资料查询条件", required = true) @RequestBody CustomerDetailSearchable customerDetailSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<CustomerDetail> customerDetailPage = customerDetailService.findAll(customerDetailSearchable, pageable);
        Page<CustomerDetailVO> customerDetailVOPage = CustomerDetail.convert(customerDetailPage, CustomerDetailVO.class);
        return Result.of(customerDetailVOPage);
    }

}