package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.GuaranteeCompanyAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuaranteeCompanyTO;
import com.cloudkeeper.leasing.company.controller.GuaranteeCompanyController;
import com.cloudkeeper.leasing.company.domain.GuaranteeCompany;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanyDTO;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanySearchable;
import com.cloudkeeper.leasing.company.service.GuaranteeCompanyService;
import com.cloudkeeper.leasing.company.vo.GuaranteeCompanyAllVO;
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
import java.util.Optional;

/**
 * 担保公司 controller
 *
 * @author asher
 */
@RestController
public class GuaranteeCompanyControllerImpl implements GuaranteeCompanyController {

    /** 担保公司 service*/
    @Autowired
    private GuaranteeCompanyService guaranteeCompanyService;

    @Override
    public Result<GuaranteeCompanyAllVO> findOne(@ApiParam(value = "担保公司id", required = true) @PathVariable String id) {
        GuaranteeCompany guaranteeCompany = guaranteeCompanyService.findById(id);
        if (guaranteeCompany == null) {
            return Result.ofNotFound();
        }
        return Result.of(guaranteeCompany.convert());
    }

    @Override
    public Result<GuaranteeCompanyAllVO> add(@ApiParam(value = "担保公司 DTO", required = true) @RequestBody @Validated GuaranteeCompanyDTO guaranteeCompanyDTO) {
        GuaranteeCompany guaranteeCompany = new GuaranteeCompany();
        guaranteeCompany = guaranteeCompanyService.save(guaranteeCompanyDTO, guaranteeCompany);
        return Result.ofAddSuccess(guaranteeCompany.convert());
    }

    @Override
    public Result<GuaranteeCompanyAllVO> update(@ApiParam(value = "担保公司id", required = true) @PathVariable String id,
                                                @ApiParam(value = "担保公司 DTO", required = true) @RequestBody @Validated GuaranteeCompanyDTO guaranteeCompanyDTO) {
        Optional<GuaranteeCompany> guaranteeCompanyOptional = guaranteeCompanyService.findOptionalById(id);
        if (!guaranteeCompanyOptional.isPresent()) {
            return Result.ofLost();
        }
        GuaranteeCompany guaranteeCompany = guaranteeCompanyService.save(guaranteeCompanyDTO, guaranteeCompanyOptional.get());
        return Result.ofUpdateSuccess(guaranteeCompany.convert());
    }

    @Override
    public Result delete(@ApiParam(value = "担保公司id", required = true) @PathVariable String id) {
        guaranteeCompanyService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<GuaranteeCompanyAllVO>> list(@ApiParam(value = "担保公司查询条件", required = true) @RequestBody GuaranteeCompanySearchable guaranteeCompanySearchable,
                                                    @ApiParam(value = "排序条件", required = true) Sort sort) {
        BooleanBuilder booleanBuilder = guaranteeCompanyService.findByBuilder(guaranteeCompanySearchable);
        Iterable<GuaranteeCompany> guaranteeCompanies = guaranteeCompanyService.findAll(booleanBuilder, sort);
        List<GuaranteeCompany> guaranteeCompanyList = GuaranteeCompany.convert(guaranteeCompanies, GuaranteeCompany.class);
        List<GuaranteeCompanyAllVO> companyVOList = guaranteeCompanyService.toVO(guaranteeCompanyList);
        return Result.of(companyVOList);
    }

    @Override
    public Result<Page<GuaranteeCompanyAllVO>> page(@ApiParam(value = "担保公司查询条件", required = true) @RequestBody GuaranteeCompanySearchable guaranteeCompanySearchable,
                                                    @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        BooleanBuilder booleanBuilder = guaranteeCompanyService.findByBuilder(guaranteeCompanySearchable);
        Page<GuaranteeCompany> guaranteeCompanyPage = guaranteeCompanyService.findAll(booleanBuilder, pageable);
        List<GuaranteeCompanyAllVO> companyVOS = guaranteeCompanyService.toVO(guaranteeCompanyPage.getContent());
        Page<GuaranteeCompanyAllVO> companyVOPage = new PageImpl<>(companyVOS, pageable, guaranteeCompanyPage.getTotalElements());
        return Result.of(companyVOPage);
    }

    @Override
    public GuaranteeCompanyAllTO findByHistory(@ApiParam(value = "担保公司历史hsId", required = true) @PathVariable String hsId) {
        return  guaranteeCompanyService.getHisTO(hsId);
    }

    @Override
    public GuaranteeCompanyAllTO findByNewHistory(@ApiParam(value = "担保公司历史hsId", required = true) @PathVariable String hsId) {
        return guaranteeCompanyService.getLastHisTO(hsId);
    }

    @Override
    public List<GuaranteeCompanyAllTO> findByHisList(@ApiParam(value = "担保公司id集合", required = true) @RequestBody List<String> ids) {
        return guaranteeCompanyService.getHisListTO(ids);
    }

    @Override
    public List<GuaranteeCompanyAllTO> findByLastHisList(@ApiParam(value = "担保公司id集合", required = true) @RequestBody List<String> ids) {
        return guaranteeCompanyService.getLastHisListTO(ids);
    }

}