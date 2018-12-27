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
 * 所站人员
 * @author wj
 */
@ApiModel(value = "所站人员", description = "所站人员")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_town_user")
public class TownUser extends BaseEntity {

    @ApiModelProperty(value = "所站id", position = 11, required = true)
    private String townId;

    @ApiModelProperty(value = "工号", position = 13, required = true)
    private String jobNumber;

    @ApiModelProperty(value = "姓名", position = 15)
    private String name;

    @ApiModelProperty(value = "性别", position = 17)
    private String gender;

    @ApiModelProperty(value = "职位", position = 19)
    private String position;

    @ApiModelProperty(value = "备注", position = 21)
    private String remark;

}