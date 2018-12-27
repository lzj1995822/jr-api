package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.SupplierAllTO;
import com.cloudkeeper.leasing.bean.company.to.SupplierTO;
import com.cloudkeeper.leasing.company.controller.SupplierController;
import com.cloudkeeper.leasing.company.domain.Supplier;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierDTO;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierSearchable;
import com.cloudkeeper.leasing.company.service.CommonCompanyService;
import com.cloudkeeper.leasing.company.service.SupplierService;
import com.cloudkeeper.leasing.company.vo.SupplierAllVO;
import com.cloudkeeper.leasing.company.vo.SupplierVO;
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

/**
 * 供应商 controller
 * @author asher
 */
@RestController
public class SupplierControllerImpl implements SupplierController {

    /** 供应商 service */
    @Autowired
    private SupplierService supplierService;

    /** 主表公司 service */
    @Autowired
    private CommonCompanyService commonCompanyService;

    @Autowired
    public SupplierControllerImpl(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public Result<SupplierVO> findOne(@ApiParam(value = "供应商id", required = true) @PathVariable String id) {
        Supplier supplier = supplierService.getOne(id);
        if (supplier == null) {
            return Result.ofNotFound();
        }
        return Result.of(supplier.convert());
    }

    @Override
    public Result<SupplierVO> add(@ApiParam(value = "供应商 DTO", required = true) @RequestBody @Validated SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier = supplierService.save(supplierDTO, supplier);
        return Result.ofAddSuccess(supplier.convert());
    }

    @Override
    public Result<SupplierVO> update(@ApiParam(value = "供应商id", required = true) @PathVariable String id,
        @ApiParam(value = "供应商 DTO", required = true) @RequestBody @Validated SupplierDTO supplierDTO) {
        Optional<Supplier> supplierOptional = supplierService.findOptionalById(id);
        if (!supplierOptional.isPresent()) {
            return Result.ofLost();
        }
        Supplier supplier = supplierOptional.get();
        supplier = supplierService.save(supplierDTO, supplier);
        return Result.ofUpdateSuccess(supplier.convert());
    }

    @Override
    public Result delete(@ApiParam(value = "供应商id", required = true) @PathVariable String id) {
        supplierService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<SupplierAllVO>> list(@ApiParam(value = "供应商查询条件", required = true) @RequestBody SupplierSearchable supplierSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        BooleanBuilder booleanBuilder = supplierService.defaultBooleanBuilder(supplierSearchable);
        Iterable<Supplier> all = supplierService.findAll(booleanBuilder, sort);
        List<Supplier> supplierList = Supplier.convert(all, Supplier.class);
        List<SupplierAllVO> supplierVOList = supplierService.toVO(supplierList);
        return Result.of(supplierVOList);
    }

    @Override
    public Result<Page<SupplierAllVO>> page(@ApiParam(value = "供应商查询条件", required = true) @RequestBody SupplierSearchable supplierSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        BooleanBuilder booleanBuilder = supplierService.defaultBooleanBuilder(supplierSearchable);
        Page<Supplier> supplierPage = supplierService.findAll(booleanBuilder, pageable);
        List<SupplierAllVO> supplierVOS = supplierService.toVO(supplierPage.getContent());
        Page<SupplierAllVO> supplierVOPage = new PageImpl<>(supplierVOS, pageable, supplierPage.getTotalElements());
        return Result.of(supplierVOPage);
    }

    @Override
    public Result<SupplierAllVO> getHis(@ApiParam(value = "供应商历史id", required = true) @PathVariable String hisId) {
        SupplierAllVO supplierAllVO = supplierService.getHisVO(hisId);
        if (supplierAllVO == null) {
            return Result.ofNotFound();
        }
        return Result.of(supplierAllVO);
    }

    @Override
    public Result<SupplierAllVO> getHisByBusinessId(@ApiParam(value = "供应商业务id", required = true) @PathVariable String businessId) {
        SupplierAllVO supplierAllVO = supplierService.getHisVOByBusinessId(businessId);
        if (supplierAllVO == null) {
            return Result.ofNotFound();
        }
        return Result.of(supplierAllVO);
    }

    @Override
    public SupplierAllTO getHisTO(@ApiParam(value = "供应商历史id", required = true) @PathVariable String hisId) {
        SupplierAllTO supplierAllTO = supplierService.getHisTO(hisId);
        return supplierAllTO;
    }

    @Override
    public SupplierAllTO getHisTOByBusinessId(@ApiParam(value = "供应商业务id", required = true) @PathVariable String businessId) {
        SupplierAllTO supplierAllTO = supplierService.getHisTOByBusinessId(businessId);
        return supplierAllTO;
    }

    @Override
    public Result<Map<String, String>> getIdAndName() {
        Map<String, String> map = commonCompanyService.getHisIdAndNameMap(supplierService.getTableName());
        return Result.of(map);
    }

    @Override
    public SupplierAllTO getLatestHisTOByHisId(@ApiParam(value = "供应商历史id", required = true) @PathVariable String hisId) {
        SupplierAllTO supplierAllTO = supplierService.getLatestHisByHisId(hisId);
        return supplierAllTO;
    }

    @Override
    public List<SupplierTO> getLatestIdAndNameMap(@RequestBody List<String> hisIdList) {
        List<SupplierTO> supplierTOS = commonCompanyService.getLatestHisListByHisId(SupplierTO.class, hisIdList, supplierService.getTableName());
        return supplierTOS;
    }
}