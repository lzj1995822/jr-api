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
 * 活动 VO
 * @author wj
 */
@ApiModel(value = "活动 VO", description = "活动 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityVO extends BaseVO {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 12)
    private String des;

    /** 图片url */
    @ApiModelProperty(value = "图片url", position = 14)
    private String url;

    /** 得分 */
    @ApiModelProperty(value = "得分", position = 16)
    private Double score;

    /** 自选还是分中心计划 */
    @ApiModelProperty(value = "自选还是分中心计划", position = 18)
    private String type;

    /** 流程状态 */
    @ApiModelProperty(value = "流程状态", position = 20)
    private String status;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间", position = 22)
    private LocalDateTime beginAt;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间", position = 24)
    private LocalDateTime endAt;

    /** 是否为特色活动 */
    @ApiModelProperty(value = "是否为特色活动", position = 26)
    private String isSpecial;

    /** 活动类型 */
    @ApiModelProperty(value = "活动类型", position = 28)
    private String activityType;

}