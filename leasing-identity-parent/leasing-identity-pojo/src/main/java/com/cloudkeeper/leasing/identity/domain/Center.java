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
@Table(name = "td_center")
public class Center extends BaseEntity {

    @ApiModelProperty(value = "编码", position = 11, required = true)
    private String coding;
    
    @ApiModelProperty(value = "分中心名称", position = 13, required = true)
    private String name;
    
    @ApiModelProperty(value = "文化类别", position = 15)
    private String culturalType;
    
    @ApiModelProperty(value = "经度", position = 17)
    private String longitude;
    
    @ApiModelProperty(value = "纬度", position = 19)
    private String latitude;
    
    @ApiModelProperty(value = "备注", position = 21)
    private String remark;

}