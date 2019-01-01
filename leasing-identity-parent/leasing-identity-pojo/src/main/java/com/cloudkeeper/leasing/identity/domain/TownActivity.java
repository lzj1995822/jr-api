package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 镇活动
 * @author hf
 */
@ApiModel(value = "镇活动", description = "镇活动")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_town_activity")
public class TownActivity extends BaseEntity {

    /** 镇id */
    @ApiModelProperty(value = "镇id", position = 10)
    private String townId;

    /** 活动id */
    @ApiModelProperty(value = "活动id", position = 12)
    private String activityId;


}