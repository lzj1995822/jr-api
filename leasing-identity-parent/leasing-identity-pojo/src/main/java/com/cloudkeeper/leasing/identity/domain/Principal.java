package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 用户
 * @author jerry
 */
@ApiModel(value = "用户", description = "用户")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_user")
public class Principal extends BaseEntity {

    /** 登录名 */
    @ApiModelProperty(value = "登录名", position = 10, required = true)
    @Column(length = 30)
    private String code;

    /** 姓名 */
    @ApiModelProperty(value = "姓名", position = 12, required = true)
    @Column(length = 50)
    private String name;

    /** 密码 */
    @ApiModelProperty(value = "密码", position = 14, required = true)
    @Column(length = 50)
    private String password;

    /** 手机 */
    @ApiModelProperty(value = "手机", position = 16)
    @Column(length = 11)
    private String mobile;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱", position = 18)
    @Column(length = 100)
    private String email;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 20)
    @Column(length = 1000)
    private String note;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 22)
    @Column(length = 200)
    private String remark;

    /** 角色id */
    @ApiModelProperty(value = "角色id", position = 24)
    private String roleId;

    @OneToOne
    @JoinColumn(name = "roleId", updatable = false, insertable = false)
    private Role role;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 26)
    private String orgId;

    /** 组织类型 */
    @ApiModelProperty(value = "组织类型", position = 28)
    private String type;

}
