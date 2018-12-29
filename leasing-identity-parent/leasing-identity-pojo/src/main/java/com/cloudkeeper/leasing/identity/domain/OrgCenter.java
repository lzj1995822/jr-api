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
 * 分中心
 * @author wj
 */
@ApiModel(value = "分中心", description = "分中心")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_org_center")
public class OrgCenter extends BaseEntity {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 名称 */
    @ApiModelProperty(value = "统一编码", position = 12)
    private String codeNumber;

    /** 名称 */
    @ApiModelProperty(value = "类型", position = 14)
    private String type;

    /** 名称 */
    @ApiModelProperty(value = "城市id", position = 16)
    private String cityId;

    /** 名称 */
    @ApiModelProperty(value = "经度", position = 18)
    private String locateX;

    /** 名称 */
    @ApiModelProperty(value = "纬度", position = 20)
    private String locateY;

}