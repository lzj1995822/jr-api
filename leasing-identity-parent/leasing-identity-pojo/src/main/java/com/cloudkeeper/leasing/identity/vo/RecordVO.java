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
 * 活动记录 VO
 * @author wj
 */
@ApiModel(value = "活动记录 VO", description = "活动记录 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class RecordVO extends BaseVO {

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


}