package com.cloudkeeper.leasing.identity.dto.record;

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
 * 活动记录 查询DTO
 * @author wj
 */
@ApiModel(value = "活动记录 查询DTO", description = "活动记录 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecordSearchable extends BaseSearchable {

    /** 活动id */
    @ApiModelProperty(value = "活动id", position = 10)
    private String activityId;

    /** 村id */
    @ApiModelProperty(value = "村id", position = 12)
    private String countryId;

    /** 流程状态 */
    @ApiModelProperty(value = "流程状态", position = 14)
    private String status;

    /** 活动内容 */
    @ApiModelProperty(value = "活动内容", position = 16)
    private String content;

    /** 积分 */
    @ApiModelProperty(value = "积分", position = 18)
    private Double score;

}