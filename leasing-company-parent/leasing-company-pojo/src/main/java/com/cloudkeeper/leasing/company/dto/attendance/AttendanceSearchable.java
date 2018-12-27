package com.cloudkeeper.leasing.company.dto.attendance;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel(value = "出勤查询searchable", description = "出勤查询searchable")
public class AttendanceSearchable extends BaseSearchable {

    /** 车辆信息表id*/
    @ApiModelProperty(value = "车辆信息表id", position = 11)
    private String vehiclesId;

    /** 司机信息表id*/
    @ApiModelProperty(value = "司机信息表id", position = 15)
    private String driversId;

    /** 具体关联的业务id*/
    @ApiModelProperty(value = "具体关联的业务id", position = 19)
    private String itemId;

    /** 事由*/
    @ApiModelProperty(value = "事由", position = 21)
    private String cause;

    /** 出勤开始时间*/
    @ApiModelProperty(value = "出勤开始时间", position = 23)
    private Date startTime;

    /** 出勤结束时间*/
    @ApiModelProperty(value = "出勤结束时间", position = 25)
    private Date endTime;

    /** 起始里程*/
    @ApiModelProperty(value = "起始里程", position = 27)
    private String startMileage;

    /** 结束里程*/
    @ApiModelProperty(value = "结束里程", position = 29)
    private String endMileage;

    /** 车牌号*/
    @ApiModelProperty(value = "车牌号", position = 31)
    private String vehicleNumber;

    /** 司机姓名*/
    @ApiModelProperty(value = "司机姓名", position = 33)
    private String driverName;
}
