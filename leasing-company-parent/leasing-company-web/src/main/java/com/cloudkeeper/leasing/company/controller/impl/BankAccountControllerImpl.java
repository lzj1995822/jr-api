package com.cloudkeeper.leasing.company.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.company.controller.BankAccountController;
import com.cloudkeeper.leasing.company.domain.BankAccount;
import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountDTO;
import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountSearchable;
import com.cloudkeeper.leasing.company.service.BankAccountService;
import com.cloudkeeper.leasing.company.vo.BankAccountVO;
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
 * 银行账号 controller
 * @author asher
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BankAccountControllerImpl implements BankAccountController {

    /** 银行账号 service */
    private final BankAccountService bankAccountService;

    @Override
    public Result<BankAccountVO> findOne(@ApiParam(value = "银行账号id", required = true) @PathVariable String id) {
        Optional<BankAccount> bankAccountOptional = bankAccountService.findOptionalById(id);
        return bankAccountOptional.map(bankAccount -> Result.of(bankAccount.convert(BankAccountVO.class))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<BankAccountVO> add(@ApiParam(value = "银行账号 DTO", required = true) @RequestBody @Validated BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = bankAccountService.save(bankAccountDTO.convert(BankAccount.class));
        return Result.ofAddSuccess(bankAccount.convert(BankAccountVO.class));
    }

    @Override
    public Result<BankAccountVO> update(@ApiParam(value = "银行账号id", required = true) @PathVariable String id,
        @ApiParam(value = "银行账号 DTO", required = true) @RequestBody @Validated BankAccountDTO bankAccountDTO) {
        Optional<BankAccount> bankAccountOptional = bankAccountService.findOptionalById(id);
        if (!bankAccountOptional.isPresent()) {
            return Result.ofLost();
        }
        BankAccount bankAccount = bankAccountOptional.get();
        BeanUtils.copyProperties(bankAccountDTO, bankAccount);
        bankAccount = bankAccountService.save(bankAccount);
        return Result.ofUpdateSuccess(bankAccount.convert(BankAccountVO.class));
    }

    @Override
    public Result delete(@ApiParam(value = "银行账号id", required = true) @PathVariable String id) {
        bankAccountService.deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<BankAccountVO>> list(@ApiParam(value = "银行账号查询条件", required = true) @RequestBody BankAccountSearchable bankAccountSearchable,
        @ApiParam(value = "排序条件", required = true) Sort sort) {
        List<BankAccount> bankAccountList = bankAccountService.findAll(bankAccountSearchable, sort);
        List<BankAccountVO> bankAccountVOList = BankAccount.convert(bankAccountList, BankAccountVO.class);
        return Result.of(bankAccountVOList);
    }

    @Override
    public Result<Page<BankAccountVO>> page(@ApiParam(value = "银行账号查询条件", required = true) @RequestBody BankAccountSearchable bankAccountSearchable,
        @ApiParam(value = "分页参数", required = true) Pageable pageable) {
        Page<BankAccount> bankAccountPage = bankAccountService.findAll(bankAccountSearchable, pageable);
        Page<BankAccountVO> bankAccountVOPage = BankAccount.convert(bankAccountPage, BankAccountVO.class);
        return Result.of(bankAccountVOPage);
    }

}