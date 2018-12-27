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
 * 所站
 * @author wj
 */
@ApiModel(value = "所站", description = "所站")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_town")
public class Town extends BaseEntity {

    @ApiModelProperty(value = "编码", position = 11, required = true)
    private String coding;

    @ApiModelProperty(value = "所站名称", position = 13, required = true)
    private String townName;

    @ApiModelProperty(value = "经度", position = 15)
    private String longitude;

    @ApiModelProperty(value = "纬度", position = 17)
    private String latitude;

    @ApiModelProperty(value = "备注", position = 19)
    private String remark;

}