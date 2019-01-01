package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 特殊活动
 * @author hf
 */
@ApiModel(value = "特殊活动", description = "特殊活动")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_record_special")
public class RecordSpecial extends BaseEntity {

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
    @ApiModelProperty(value = "积分", position = 20)
    private Double score;

    /** 意见 */
    @ApiModelProperty(value = "意见", position = 22)
    private String opinion;


    /** 志愿者 */
    @ApiModelProperty(value = "志愿者", position = 24)
    private String volunteers;

    @OneToOne
    @JoinColumn(name = "createdBy", updatable = false, insertable = false)
    private Principal creator;

    /** 活动 */
    @ApiModelProperty(value = "活动", position = 26, required = true)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "activityId", insertable = false, updatable = false)
    private Activity activity;

    /** 活动执行id */
    @ApiModelProperty(value = "活动执行id", position = 28)
    private String recordId;

}