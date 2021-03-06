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
 * 镇文明实践点
 * @author wj
 */
@ApiModel(value = "镇文明实践点", description = "镇文明实践点")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_town_practice_point")
public class TownPracticePoint extends BaseEntity {

    @ApiModelProperty(value = "功能室id", position = 11, required = true)
    private String functionRoomId;

    @ApiModelProperty(value = "经度", position = 13, required = true)
    private String longitude;

    @ApiModelProperty(value = "纬度", position = 15)
    private String latitude;

    @ApiModelProperty(value = "备注", position = 17)
    private String remark;


}