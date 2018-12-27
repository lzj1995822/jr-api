package com.cloudkeeper.leasing.company.dto.customer;

import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanySearchable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 客户查询DTO
 * @author jerry
 */
@Getter
@Setter
@ApiModel(value = "客户查询DTO", description = "客户查询DTO")
public class CustomerSearchable extends CommonCompanySearchable {

    /** 公司父表id*/
    @ApiModelProperty(value = "公司父表id", position = 11)
    private String parentId;

    /** 职工人数*/
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 公司电话*/
    @ApiModelProperty(value = "公司电话", position = 19)
    private String phoneNumber;

    /** 工厂地址--省*/
    @ApiModelProperty(value = "工厂地址--省", position = 21)
    private String factoryProvince;

    /** 工厂地址--市*/
    @ApiModelProperty(value = "工厂地址--市", position = 23)
    private String factoryCity;

    /** 工厂地址--详细地址*/
    @ApiModelProperty(value = "工厂地址--详细地址", position = 25)
    private String factoryDetailAddress;
}
