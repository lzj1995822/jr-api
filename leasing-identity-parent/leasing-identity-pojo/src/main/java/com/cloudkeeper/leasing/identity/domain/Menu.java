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
 * 菜单
 * @author wj
 */
@ApiModel(value = "菜单", description = "菜单")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_menu")
public class Menu extends BaseEntity {

    /** 名称 */
    @ApiModelProperty(value = "菜单名称", position = 10)
    private String name;

    /** 菜单地址 */
    @ApiModelProperty(value = "菜单地址", position = 12)
    private String url;

    /** 父id */
    @ApiModelProperty(value = "父id", position = 10)
    private String parentId;

}