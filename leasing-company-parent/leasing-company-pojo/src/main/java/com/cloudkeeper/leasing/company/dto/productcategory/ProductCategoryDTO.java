package com.cloudkeeper.leasing.company.dto.productcategory;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 产品分类 DTO
 * @author asher
 */
@ApiModel(value = "产品分类 DTO", description = "产品分类 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryDTO extends BaseEditDTO {

    /** 类别名字 */
    @ApiModelProperty(value = "类别名字", position = 11, required = true)
    private String name;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 13, required = true)
    private String supplierId;

    /** 厂牌 */
    @ApiModelProperty(value = "厂牌", position = 17)
    private String brand;

    /** 产地类型 */
    @ApiModelProperty(value = "产地类型", position = 19)
    private String originType;

}