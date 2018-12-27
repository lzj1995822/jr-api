package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.CustomerAllTO;
import com.cloudkeeper.leasing.company.controller.CustomerController;
import com.cloudkeeper.leasing.company.domain.Customer;
import com.cloudkeeper.leasing.company.dto.customer.CustomerDTO;
import com.cloudkeeper.leasing.company.dto.customer.CustomerSearchable;
import com.cloudkeeper.leasing.company.service.CommonCompanyService;
import com.cloudkeeper.leasing.company.service.CustomerService;
import com.cloudkeeper.leasing.company.vo.CustomerAllVO;
import com.cloudkeeper.leasing.company.vo.CustomerVO;
import com.querydsl.core.BooleanBuilder;
import io.swagger.annotations.ApiParam;
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
import java.util.Map;
import java.util.Optional;

@RestController
public class CustomerControllerImpl implements CustomerController {

    /** 客户 service*/
    @Autowired
    private CustomerService customerService;

    /** 公有公司 service*/
    @Autowired
    private CommonCompanyService commonCompanyService;

    @Override
    public Result<CustomerVO> findOne(@ApiParam(value = "客户id", required = true) @PathVariable String id) {
        Customer customer = customerService.getOne(id);
        if (customer == null) {
            return Result.ofNotFound();
        }
        return Result.of(customer.convert());
    }

    @Override
    public Result<CustomerVO> add(@ApiParam(value = "客户dto", required = true) @RequestBody @Validated CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer = customerService.save(customerDTO, customer);
        return Result.ofAddSuccess(customer.convert());
    }

    @Override
    public Result<CustomerVO> update(@ApiParam(value = "客户id", required = true) @PathVariable String id,
                                     @ApiParam(value = "客户dto", required = true) @RequestBody @Validated CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerService.findOptionalById(id);
        if (!customerOptional.isPresent()) {
            return Result.of(Result.ResultCode.FAIL.getCode(), "修改的数据不存在，请刷新后重试！");
        }
        Customer customer = customerService.save(customerDTO, customerOptional.get());
        return Result.ofUpdateSuccess(customer.convert());
    }

    @Override
    public Result delete(@ApiParam(value = "客户id", required = true) @PathVariable String id) {
        customerService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<CustomerAllVO>> list(@ApiParam(value = "客户查询条件", required = true) @RequestBody CustomerSearchable customerSearchable,
                                         @ApiParam(value = "排序条件", required = true) Sort sort) {
        BooleanBuilder booleanBuilder = customerService.defaultBooleanBuilder(customerSearchable);
        Iterable<Customer> all = customerService.findAll(booleanBuilder, sort);
        List<Customer> customerList = Customer.convert(all,Customer.class);
        List<CustomerAllVO> customerVOList = customerService.toVO(customerList);
        return Result.of(customerVOList);
    }

    @Override
    public Result<Page<CustomerAllVO>> page(@ApiParam(value = "客户查询条件", required = true) @RequestBody CustomerSearchable customerSearchable,
                                         @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        BooleanBuilder booleanBuilder = customerService.defaultBooleanBuilder(customerSearchable);
        Page<Customer> customerPage = customerService.findAll(booleanBuilder, pageable);
        List<CustomerAllVO> customerVOS = customerService.toVO(customerPage.getContent());
        Page<CustomerAllVO> customerVOPage = new PageImpl<>(customerVOS, pageable, customerPage.getTotalElements());
        return Result.of(customerVOPage);
    }

    @Override
    public Result<CustomerAllVO> getHis(@ApiParam(value = "客户历史id", required = true) @PathVariable String hisId) {

        CustomerAllVO customerAllVO = customerService.getHisVO(hisId);
        if (customerAllVO == null) {
            return Result.ofNotFound();
        }
        return Result.of(customerAllVO);
    }

    @Override
    public Result<CustomerAllVO> getHisByBusinessId(@ApiParam(value = "客户业务id", required = true) @PathVariable String businessId) {
        CustomerAllVO customerAllVO = customerService.getHisVOByBusinessId(businessId);
        if (customerAllVO == null) {
            return Result.ofNotFound();
        }
        return Result.of(customerAllVO);
    }

    @Override
    public CustomerAllTO getHisTO(@ApiParam(value = "客户历史id", required = true) @PathVariable String hisId) {
        CustomerAllTO customerAllTO = customerService.getHisTO(hisId);
        return customerAllTO;
    }

    @Override
    public CustomerAllTO getHisTOByBusinessId(@ApiParam(value = "客户业务id", required = true) @PathVariable String businessId) {
        CustomerAllTO customerAllTO = customerService.getHisTOByBusinessId(businessId);
        return customerAllTO;
    }

    @Override
    public Result<Map<String, String>> getIdAndName() {
        Map<String, String> map = commonCompanyService.getHisIdAndNameMap(customerService.getTableName());
        return Result.of(map);
    }

    @Override
    public CustomerAllTO getLatestHisTOByHisId(@ApiParam(value = "客户历史id", required = true) @PathVariable String hisId) {
        CustomerAllTO customerAllTO = customerService.getLatestHisByHisId(hisId);
        return customerAllTO;
    }
}
