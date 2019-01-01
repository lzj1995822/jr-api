package com.cloudkeeper.leasing.identity.dto.recordspecial;

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
 * 特殊活动 查询DTO
 * @author hf
 */
@ApiModel(value = "特殊活动 查询DTO", description = "特殊活动 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecordSpecialSearchable extends BaseSearchable {

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

    /** 是否为特色活动 */
    @ApiModelProperty(value = "是否为特色活动", position = 18)
    private Integer isSpecial;

    /** 积分 */
    @ApiModelProperty(value = "积分", position = 20)
    private Double score;

    /** 意见 */
    @ApiModelProperty(value = "意见", position = 22)
    private String opinion;

    /** 志愿者 */
    @ApiModelProperty(value = "志愿者", position = 24)
    private String volunteers;

    /** 活动执行id */
    @ApiModelProperty(value = "活动执行id", position = 28)
    private String recordId;

}