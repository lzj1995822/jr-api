package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 角色菜单关联关系，中间表
 * @author jerry
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_role_menu")
public class RoleMenu extends BaseEntity {

    /** 角色id */
    @ApiModelProperty(value = "角色id", position = 10, required = true)
    @Column(length = 36)
    private String roleId;

    /** 角色 */
    @ApiModelProperty(value = "角色", position = 11, required = true)
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roleId", insertable = false, updatable = false)
    private Role role;

    /** 菜单编码 */
    @ApiModelProperty(value = "菜单编码", position = 13, required = true)
    @Column(length = 50)
    private String menuId;

//    /** 菜单 */
//    @ApiModelProperty(value = "菜单", position = 15)
//    @OneToOne
//    @JoinColumn(name = "menuId", insertable = false, updatable = false)
//    private Menu menu;
}
