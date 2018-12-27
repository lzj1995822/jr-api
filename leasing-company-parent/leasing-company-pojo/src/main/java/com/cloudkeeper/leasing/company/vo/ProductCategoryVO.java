package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 产品分类 VO
 * @author asher
 */
@ApiModel(value = "产品分类 VO", description = "产品分类 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryVO extends BaseVO {

    /** 类别名字 */
    @ApiModelProperty(value = "类别名字", position = 11)
    private String name;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 13)
    private String supplierId;

    /** 厂牌 */
    @ApiModelProperty(value = "厂牌", position = 17)
    private String brand;

    /** 产地类型 */
    @ApiModelProperty(value = "产地类型", position = 19)
    private String originType;

    /** 供应商名称 */
    @ApiModelProperty(value = "供应商名称", position = 21)
    private String supplierName;
}