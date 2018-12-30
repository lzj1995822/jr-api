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
 * 实践点
 * @author wj
 */
@ApiModel(value = "实践点", description = "实践点")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_point")
public class Point extends BaseEntity {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 实践点类型 */
    @ApiModelProperty(value = "实践点类型", position = 10)
    private String type;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 10)
    private String orgId;

    /** 组织类型 */
    @ApiModelProperty(value = "组织类型", position = 10)
    private String orgType;

}