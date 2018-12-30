package com.cloudkeeper.leasing.identity.dto.recordhistory;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
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
 * 活动记录历史 DTO
 * @author wj
 */
@ApiModel(value = "活动记录历史 DTO", description = "活动记录历史 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecordHistoryDTO extends BaseEditDTO {

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

    /** 意见 */
    @ApiModelProperty(value = "意见", position = 20)
    private String opinion;
    
    /** 记录id */
    @ApiModelProperty(value = "记录id", position = 22)
    private String recordId;
    
    /** 是否特殊 */
    @ApiModelProperty(value = "是否特殊", position = 24)
    private Integer isSpecial;
    
    /** 志愿者 */
    @ApiModelProperty(value = "志愿者", position = 26)
    private String volunteers;

}