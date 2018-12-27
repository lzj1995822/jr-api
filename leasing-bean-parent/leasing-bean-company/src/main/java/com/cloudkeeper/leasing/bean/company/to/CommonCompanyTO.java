package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class CommonCompanyTO extends BaseTO {

    /** 外键至意向客户表*/
    private String intentionalCustomersId;

    /** 公司名称*/
    private String name;

    /** 统一信用代码*/
    private String uniformCreditCode;

    /** 资本类型*/
    private String capitalType;

    /** 国别*/
    private String country;

    /** 注册资金*/
    private BigDecimal registeredFund;

    /** 币别*/
    private String currency;

    /** 实收资金*/
    private BigDecimal paidCapital;

    /** 注册地址--省*/
    private String province;

    /** 注册地址--市*/
    private String city;

    /** 注册地址--详细地址*/
    private String detailAddress;

    /** 成立日期*/
    private LocalDate establishAt;

    /** 实际经营地址--省*/
    private String businessProvince;

    /** 实际经营地址--市*/
    private String businessCity;

    /** 实际经营地址--详细地址*/
    private String businessDetailAddress;

    /** 营业期限--开始*/
    private LocalDate operateStart;

    /** 营业期限--结束*/
    private LocalDate operateEnd;

    /** 行业分类*/
    private String category;

    /** 主营业务*/
    private String mainBusiness;

    /** 银行名称*/
    private String bankName;

    /** 银行账户*/
    private String bankAccount;

    /** 历史主键id*/
    private String pkId;

    /** 版本*/
    private Integer version;

}
