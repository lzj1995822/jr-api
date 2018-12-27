package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "车辆vo", description = "车辆vo")
@Getter
@Setter
public class VehicleVO extends BaseVO{

    /** 车牌号码*/
    @ApiModelProperty(value = "车牌号码", position = 11, required = true)
    private String licensePlate;

    /** 最大载客数*/
    @ApiModelProperty(value = "最大载客数", position = 13)
    private Integer maximumPassenger;

    /** 型号*/
    @ApiModelProperty(value = "型号", position = 15, required = true)
    private String model;
}
