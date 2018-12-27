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
 * 分中心文明实践点
 * @author wj
 */
@ApiModel(value = "分中心文明实践点", description = "分中心文明实践点")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_center_practice_point")
public class CenterPracticePoint extends BaseEntity {

    @ApiModelProperty(value = "分中心id", position = 11, required = true)
    private String centerId;

    @ApiModelProperty(value = "经度", position = 13, required = true)
    private String longitude;

    @ApiModelProperty(value = "纬度", position = 15)
    private String latitude;

    @ApiModelProperty(value = "备注", position = 17)
    private String remark;

}