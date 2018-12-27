package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel(value = "拜访计划vo", description = "拜访计划vo")
@Getter
@Setter
public class VisitPlanVO extends BaseVO {

    /** 开始时间*/
    @ApiModelProperty(value = "开始时间", position = 11)
    private LocalDateTime startTime;

    /** 结束时间*/
    @ApiModelProperty(value = "结束时间", position = 13)
    private LocalDateTime endTime;

    /** 客户目的*/
    @ApiModelProperty(value = "客户目的", position = 15)
    private String purpose;

    /** 客户来源*/
    @ApiModelProperty(value = "客户来源", position = 17)
    private String source;

    /** 拜访区域（省份）*/
    @ApiModelProperty(value = "拜访区域（省份）", position = 19)
    private String province;

    /** 拜访区域（城市）*/
    @ApiModelProperty(value = "拜访区域（城市）", position = 21)
    private String city;

    /** 业务id*/
    @ApiModelProperty(value = "业务id", position = 23)
    private String itemId;

    /** 是否委托同仁*/
    @ApiModelProperty(value = "是否委托同仁", position = 25)
    private Integer isEntrust;

    /** 是否主管陪同*/
    @ApiModelProperty(value = "是否主管陪同", position = 27)
    private Integer isAccompany;

    /** 被委托人Id*/
    @ApiModelProperty(value = "被委托人Id", position = 29)
    private String mandataryId;

    /** 备注*/
    @ApiModelProperty(value = "备注", position = 31)
    private String note;

    /** 司机id*/
    @ApiModelProperty(value = "司机id", position = 33)
    private String driverId;

    /** 车辆id*/
    @ApiModelProperty(value = "车辆id", position = 35)
    private String vehicleId;

    /** 业务员名称*/
    @ApiModelProperty(value = "业务员名称", position = 37)
    private String saleName;

    /** 客户名称*/
    @ApiModelProperty(value = "客户名称", position = 39)
    private String customerName;

}
