package com.cloudkeeper.leasing.identity.dto.centerpracticepoint;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
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
 * 分中心文明实践点 查询DTO
 * @author wj
 */
@ApiModel(value = "分中心文明实践点 查询DTO", description = "分中心文明实践点 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CenterPracticePointSearchable extends BaseSearchable {

    /** 分中心id */
    @ApiModelProperty(value = "分中心id", position = 11, required = true)
    private String centerId;

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