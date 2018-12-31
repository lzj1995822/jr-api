package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(value = "活动统计列表 VO", description = "活动统计列表 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CountActivity1Vo extends BaseVO {
    /** 所名 */
    @ApiModelProperty(value = "所名", position = 10, required = true)
    private String townname;

    /** 站名 */
    @ApiModelProperty(value = "站名", position = 11, required = true)
    private String countryname;


    /** 执行状态*/
    @ApiModelProperty(value = "执行状态", position = 13, required = true)
    private String status;

    /** 执行内容*/
    @ApiModelProperty(value = "执行内容", position = 14, required = true)
    private String content;
    /** 单个执行记录的分数*/
    @ApiModelProperty(value = "单个执行记录的分数", position = 15, required = true)
    private Double score;

    /** 意见*/
    @ApiModelProperty(value = "意见", position = 16, required = true)
    private String opinion;

    /** 志愿者*/
    @ApiModelProperty(value = "志愿者", position = 18, required = true)
    private String volunteers;

    /** 活动名称*/
    @ApiModelProperty(value = "活动名称", position = 19, required = true)
    private String activityname;
    /** 活动描述*/
    @ApiModelProperty(value = "活动描述", position = 20, required = true)
    private String des;

    /** 图片*/
    @ApiModelProperty(value = "图片", position = 21, required = true)
    private String url;

    /** 活动的分数*/
    @ApiModelProperty(value = "活动的分数", position = 22, required = true)
    private Double ascore;

    /** 自选还是分中心计划*/
    @ApiModelProperty(value = "自选还是分中心计划", position = 23, required = true)
    private String type;

    /** 活动流程状态*/
    @ApiModelProperty(value = "活动流程状态", position = 24, required = true)
    private String astatus;

    /** 活动类型*/
    @ApiModelProperty(value = "活动类型", position = 25, required = true)
    private String activityType;



}
