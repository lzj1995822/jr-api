package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 * @author jerry
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_role")
public class Role extends BaseEntity {

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 10, required = true)
    @Column(length = 50)
    private String code;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12, required = true)
    @Column(length = 50)
    private String name;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 14)
    @Column(length = 1000)
    private String note;

    @OneToMany(mappedBy = "role")
    private List<RoleMenu> roleMenus;

}
