package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 车辆类
 */

@ApiModel(value = "车辆类", description = "车辆类")
@Getter
@Setter
@Entity
@Table(name = "l_cp_vehicles")
public class Vehicle extends BaseEntity {

    /** 车牌号码*/
    @ApiModelProperty(value = "车牌号码", position = 11, required = true)
    @Column(length = 36)
    private String licensePlate;

    /** 最大载客数*/
    @ApiModelProperty(value = "最大载客数", position = 13)
    private Integer maximumPassenger;

    /** 型号*/
    @ApiModelProperty(value = "型号", position = 15, required = true)
    @Column(length = 100)
    private String model;

}
