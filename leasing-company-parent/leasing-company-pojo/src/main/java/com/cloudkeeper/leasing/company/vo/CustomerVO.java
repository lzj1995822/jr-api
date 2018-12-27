package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户vo
 * @author jerry
 */
@ApiModel(value = "客户vo", description = "客户vo")
@Getter
@Setter
public class CustomerVO extends CommonCompanyVO {

    /** 公司父表id*/
    @ApiModelProperty(value = "公司父表id", position = 11)
    private String parentId;

    /** 职工人数*/
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 公司电话*/
    @ApiModelProperty(value = "公司电话", position = 17)
    private String phoneNumber;

    /** 工厂地址--省*/
    @ApiModelProperty(value = "工厂地址--省", position = 19)
    private String factoryProvince;

    /** 工厂地址--市*/
    @ApiModelProperty(value = "工厂地址--市", position = 21)
    private String factoryCity;

    /** 工厂地址--详细地址*/
    @ApiModelProperty(value = "工厂地址--详细地址", position = 23)
    private String factoryDetailAddress;

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFactoryProvince(String factoryProvince) {
        this.factoryProvince = factoryProvince;
    }

    public void setFactoryCity(String factoryCity) {
        this.factoryCity = factoryCity;
    }

    public void setFactoryDetailAddress(String factoryDetailAddress) {
        this.factoryDetailAddress = factoryDetailAddress;
    }
}
