package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 镇文明实践点 VO
 * @author wj
 */
@ApiModel(value = "镇文明实践点 VO", description = "镇文明实践点 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownPracticePointVO extends BaseVO {

    /** 功能室id */
    @ApiModelProperty(value = "功能室id", position = 11, required = true)
    private String functionRoomId;

    /** 经度 */
    @ApiModelProperty(value = "经度", position = 13, required = true)
    private String longitude;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", position = 15)
    private String latitude;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 17)
    private String remark;

}