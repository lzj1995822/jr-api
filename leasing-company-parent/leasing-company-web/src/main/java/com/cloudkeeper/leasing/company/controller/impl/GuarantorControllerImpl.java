package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.company.to.GuarantorAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuarantorTO;
import com.cloudkeeper.leasing.company.controller.GuarantorController;
import com.cloudkeeper.leasing.company.domain.Guarantor;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorDTO;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorSearchable;
import com.cloudkeeper.leasing.company.service.GuarantorService;
import com.cloudkeeper.leasing.company.vo.GuarantorAllVO;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 担保人 controller
 *
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GuarantorControllerImpl implements GuarantorController {

    /** 担保人 service*/
    private final GuarantorService guarantorService;

    @Override
    public Result<GuarantorAllVO> findOne(@ApiParam(value = "担保人id", required = true) @PathVariable String id) {
        Guarantor guarantor = guarantorService.findById(id);
        if (guarantor == null) {
            return Result.ofNotFound();
        }
        GuarantorAllVO vo = guarantorService.getVo(guarantor);
        return Result.of(vo);
    }

    @Override
    public Result<GuarantorAllVO> add(@ApiParam(value = "担保人 DTO", required = true) @RequestBody @Validated GuarantorDTO guarantorDTO) {
        Guarantor guarantor = new Guarantor();
        guarantor = guarantorService.save(guarantorDTO, guarantor);
        return Result.ofAddSuccess(guarantorService.getVo(guarantor));
    }

    @Override
    public Result<GuarantorAllVO> update(@ApiParam(value = "担保人id", required = true) @PathVariable String id,
                                         @ApiParam(value = "担保人 DTO", required = true) @RequestBody @Validated GuarantorDTO guarantorDTO) {
        Optional<Guarantor> guarantorOptional = guarantorService.findOptionalById(id);
        if (!guarantorOptional.isPresent()) {
            return Result.ofLost();
        }
        Guarantor guarantor = guarantorOptional.get();
        BeanUtils.copyProperties(guarantorDTO, guarantor);
        guarantor = guarantorService.save(guarantorDTO, guarantor);
        return Result.ofUpdateSuccess(guarantorService.getVo(guarantor));
    }

    @Override
    public Result delete(@ApiParam(value = "担保人id", required = true) @PathVariable String id) {
        guarantorService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<GuarantorAllVO>> list(@ApiParam(value = "担保人查询条件", required = true) @RequestBody GuarantorSearchable guarantorSearchable,
                                             @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<Guarantor> guarantorList = guarantorService.findAll(guarantorSearchable, sort);
        List<GuarantorAllVO> guarantorVOList = guarantorService.getListVo(guarantorList);
        return Result.of(guarantorVOList);
    }

    @Override
    public Result<Page<GuarantorAllVO>> page(@ApiParam(value = "担保人查询条件", required = true) @RequestBody GuarantorSearchable guarantorSearchable,
                                             @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<Guarantor> guarantorPage = guarantorService.findAll(guarantorSearchable, pageable);
        List<GuarantorAllVO> guarantorVOList = guarantorService.getListVo(guarantorPage.getContent());
        Page<GuarantorAllVO> guarantorVOPage = new PageImpl<>(guarantorVOList, pageable, guarantorPage.getTotalElements());
        return Result.of(guarantorVOPage);
    }

    @Override
    public GuarantorAllTO findHistory(@ApiParam(value = "担保人历史hsId", required = true) @PathVariable String hsId) {
        return guarantorService.getHisTO(hsId);
    }

    @Override
    public GuarantorAllTO findNewHistory(@ApiParam(value = "担保人历史hsId", required = true) @PathVariable String hsId) {
        return guarantorService.getLastHisTO(hsId);
    }

    @Override
    public List<GuarantorTO> findByHistList(@ApiParam(value = "担保人历史id集合", required = true) @RequestBody List<String> ids) {
        return guarantorService.getHisListTO(ids);
    }

    @Override
    public List<GuarantorTO> findByLastHistList(@ApiParam(value = "担保人历史id集合", required = true) @RequestBody List<String> ids) {
        return guarantorService.getLastHisListTO(ids);
    }

}