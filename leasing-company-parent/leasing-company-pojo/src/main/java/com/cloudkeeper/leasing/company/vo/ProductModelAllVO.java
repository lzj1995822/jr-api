package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "型号全")
@Getter
@Setter
public class ProductModelAllVO extends ProductModelVO {

    /** 供应商名称 */
    @ApiModelProperty(value = "供应商名称", position = 29)
    private String supplierName;

    /** 产品分类名称 */
    @ApiModelProperty(value = "产品分类名称", position = 31)
    private String categoryName;

}
