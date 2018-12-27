package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 产品型号 VO
 * @author asher
 */
@ApiModel(value = "产品型号 VO", description = "产品型号 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductModelVO extends BaseVO {

    /** 型号名字 */
    @ApiModelProperty(value = "型号名字", position = 11, required = true)
    private String name;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 13, required = true)
    private String supplierId;

    /** 产品分类id */
    @ApiModelProperty(value = "产品分类id", position = 15, required = true)
    private String categoryId;

    /** 使用年限 */
    @ApiModelProperty(value = "使用年限", position = 17)
    private Integer usefulLife;

    /** 单位 */
    @ApiModelProperty(value = "单位", position = 19)
    private String unit;

    /** 原始价格 */
    @ApiModelProperty(value = "原始价格", position = 21)
    private BigDecimal originalPrice;

    /** 账面价格 */
    @ApiModelProperty(value = "账面价格", position = 23)
    private BigDecimal bookPrice;

    /** 币别 */
    @ApiModelProperty(value = "币别", position = 25)
    private String currency;

    /**是否支持锁码*/
    @ApiModelProperty(value = "是否支持锁码", position = 27)
    private Integer supportCode;

    /** 供应商id */
    @ApiModelProperty(value = "历史表id", position = 29)
    private String pkId;

}