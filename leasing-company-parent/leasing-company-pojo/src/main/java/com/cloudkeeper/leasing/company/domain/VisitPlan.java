package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 拜访计划类
 */

@ApiModel(value = "拜访计划类", description = "拜访计划类")
@Getter
@Setter
@Entity
@Table(name = "l_cp_visit_plan")
public class VisitPlan extends BaseEntity{

    /** 开始时间*/
    @ApiModelProperty(value = "开始时间", position = 11)
    private LocalDateTime startTime;

    /** 结束时间*/
    @ApiModelProperty(value = "结束时间", position = 13)
    private LocalDateTime endTime;

    /** 客户目的*/
    @ApiModelProperty(value = "客户目的", position = 15)
    @Column(length = 100)
    private String purpose;

    /** 客户来源*/
    @ApiModelProperty(value = "客户来源", position = 17)
    @Column(length = 100)
    private String source;

    /** 拜访区域（省份）*/
    @ApiModelProperty(value = "拜访区域（省份）", position = 19)
    @Column(length = 100)
    private String province;

    /** 拜访区域（城市）*/
    @ApiModelProperty(value = "拜访区域（城市）", position = 21)
    @Column(length = 100)
    private String city;

    /** 业务id*/
    @ApiModelProperty(value = "业务id", position = 23)
    @Column(length = 36)
    private String itemId;

    /** 是否委托同仁*/
    @ApiModelProperty(value = "是否委托同仁", position = 25)
    private Integer isEntrust;

    /** 是否主管陪同*/
    @ApiModelProperty(value = "是否主管陪同", position = 27)
    private Integer isAccompany;

    /** 被委托人Id*/
    @ApiModelProperty(value = "被委托人Id", position = 29)
    @Column(length = 36)
    private String mandataryId;

    /** 备注*/
    @ApiModelProperty(value = "备注", position = 31)
    @Column(length = 1000)
    private String note;

    /** 司机id*/
    @ApiModelProperty(value = "司机id", position = 33)
    @Column(length = 36)
    private String driverId;

    /** 司机*/
    @ApiModelProperty(value = "司机", position = 35)
    @ManyToOne
    @JoinColumn(name = "driverId", insertable = false, updatable = false)
    private Driver driver;

    /** 车辆id*/
    @ApiModelProperty(value = "车辆id", position = 37)
    @Column(length = 36)
    private String vehicleId;

    /** 车辆*/
    @ApiModelProperty(value = "车辆", position = 39)
    @ManyToOne
    @JoinColumn(name = "vehicleId", insertable = false, updatable = false)
    private Vehicle vehicle;

}
