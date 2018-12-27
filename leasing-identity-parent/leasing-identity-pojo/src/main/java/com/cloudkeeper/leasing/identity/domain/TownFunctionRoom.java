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
 * 所站功能室建设
 * @author wj
 */
@ApiModel(value = "所站功能室建设", description = "所站功能室建设")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_town_function_room")
public class TownFunctionRoom extends BaseEntity {

    @ApiModelProperty(value = "所站id", position = 11, required = true)
    private String townId;

    @ApiModelProperty(value = "编码", position = 13, required = true)
    private String coding;

    @ApiModelProperty(value = "功能室内名称", position = 15)
    private String name;

    @ApiModelProperty(value = "文化类别", position = 17)
    private String culturalCategory;

    @ApiModelProperty(value = "备注", position = 19)
    private String remark;

}