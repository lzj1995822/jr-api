package com.cloudkeeper.leasing.company.dto.commoncompany;

import com.cloudkeeper.leasing.base.dto.BaseUpdateDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 客户、担保公司、供应商父表 DTO
 * @author asher
 */
@ApiModel(value = "客户、担保公司、供应商父表 DTO", description = "客户、担保公司、供应商父表 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonCompanyDTO extends BaseUpdateDTO {

    /** 外键至意向客户表*/
    @ApiModelProperty(value = "外键至意向客户表", position = 11)
    @Length(max = 36)
    private String intentionalCustomersId;

    /** 公司名称*/
    @ApiModelProperty(value = "公司名称", position = 15)
    @NotBlank
    @Length(max = 100)
    private String name;

    /** 统一信用代码*/
    @ApiModelProperty(value = "统一信用代码", position = 17)
    @NotBlank
    @Length(max = 100)
    private String uniformCreditCode;

    /** 资本类型*/
    @ApiModelProperty(value = "资本类型", position = 19)
    @Length(max = 100)
    private String capitalType;

    /** 国别*/
    @ApiModelProperty(value = "国别", position = 21)
    @Length(max = 36)
    private String country;

    /** 注册资金*/
    @ApiModelProperty(value = "注册资金", position = 23)
    private BigDecimal registeredFund;

    /** 币别*/
    @ApiModelProperty(value = "币别", position = 25)
    @Length(max = 36)
    private String currency;

    /** 实收资金*/
    @ApiModelProperty(value = "实收资金", position = 27)
    private BigDecimal paidCapital;

    /** 注册地址--省*/
    @ApiModelProperty(value = "注册地址--省", position = 27)
    @Length(max = 36)
    private String province;

    /** 注册地址--市*/
    @ApiModelProperty(value = "注册地址--市", position = 31)
    @Length(max = 36)
    private String city;

    /** 注册地址--详细地址*/
    @ApiModelProperty(value = "注册地址--详细地址", position = 33)
    @Length(max = 200)
    private String detailAddress;

    /** 成立日期*/
    @ApiModelProperty(value = "成立日期", position = 35)
    private LocalDate establishAt;

    /** 实际经营地址--省*/
    @ApiModelProperty(value = "实际经营地址--省", position = 37)
    @Length(max = 36)
    private String businessProvince;

    /** 实际经营地址--市*/
    @ApiModelProperty(value = "实际经营地址--市", position = 39)
    @Length(max = 36)
    private String businessCity;

    /** 实际经营地址--详细地址*/
    @ApiModelProperty(value = "实际经营地址--详细地址", position = 41)
    @Length(max = 200)
    private String businessDetailAddress;


    /** 营业期限--开始*/
    @ApiModelProperty(value = "营业期限--开始", position = 43)
    private LocalDate operateStart;

    /** 营业期限--结束*/
    @ApiModelProperty(value = "营业期限--结束", position = 45)
    private LocalDate operateEnd;

    /** 行业分类*/
    @ApiModelProperty(value = "行业分类", position = 47)
    @Length(max = 36)
    private String category;

    /** 主营业务*/
    @ApiModelProperty(value = "主营业务", position = 51)
    @Length(max = 500)
    private String mainBusiness;

    /** 银行名称*/
    @ApiModelProperty(value = "银行名称", position = 53)
    @Length(max = 36)
    private String bankName;

    /** 银行账户*/
    @ApiModelProperty(value = "银行账户", position = 55)
    @Length(max = 36)
    private String bankAccount;

}