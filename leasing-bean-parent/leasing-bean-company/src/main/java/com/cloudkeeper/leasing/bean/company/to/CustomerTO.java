package com.cloudkeeper.leasing.bean.company.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerTO extends CommonCompanyTO {

    /** 公司父表id*/
    private String parentId;

    /** 职工人数*/
    private Integer staffNumber;

    /** 公司电话*/
    private String phoneNumber;

    /** 工厂地址--省*/
    private String factoryProvince;

    /** 工厂地址--市*/
    private String factoryCity;

    /** 工厂地址--详细地址*/
    private String factoryDetailAddress;
}
