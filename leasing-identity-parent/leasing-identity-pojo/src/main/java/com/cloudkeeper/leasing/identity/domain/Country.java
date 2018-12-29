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
 * 村
 * @author wj
 */
@ApiModel(value = "村", description = "村")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_country")
public class Country extends BaseEntity {

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12)
    private String name;

    /** 积分 */
    @ApiModelProperty(value = "积分", position = 14)
    private Double score;

}