package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 客户包含子表VO
 * @author asher
 */
@ApiModel(value = "客户包含子表vo", description = "客户包含子表vo")
@Getter
@Setter
public class CustomerAllVO extends CustomerVO {

    /** 客户经理*/
    @ApiModelProperty(value = "客户经理",position = 25)
    private String customerManager;

    /** 法人DTOs*/
    @ApiModelProperty(value = "法人DTOs", position = 27)
    private CompanyPersonnelVO corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 29)
    private List<CompanyPersonnelVO> contactList;

    /** 股东董事DTOs*/
    @ApiModelProperty(value = "股东董事DTOs", position = 31)
    private List<CompanyPersonnelVO> shareholderList;

    /** 客户详细、开票资料DTO*/
    @ApiModelProperty(value = "客户详细、开票资料DTO", position = 33)
    private List<CustomerDetailVO> customerDetailList;

    /** 银行账号DTO*/
    @ApiModelProperty(value = "银行账号DTO", position = 35)
    private List<BankAccountVO> bankAccountList;

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 37)
    private List<String> contactDeleteList;

    /** 股东董事需要被删除的ID集合*/
    @ApiModelProperty(value = "股东董事需要被删除的ID集合", position = 39)
    private List<String> shareholderDeleteList;

    /** 客户详细、开票资料需要被删除的ID集合*/
    @ApiModelProperty(value = "** 客户详细、开票资料需要被删除的ID集合", position = 41)
    private List<String> customerDetailDeleteList;

    /** 银行账号需要被删除的ID集合*/
    @ApiModelProperty(value = "银行账号需要被删除的ID集合", position = 43)
    private List<String> bankAccountDeleteList;

}
