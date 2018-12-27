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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 机器编号 VO
 * @author asher
 */
@ApiModel(value = "机器编号 VO", description = "机器编号 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationNumberVO extends BaseVO {

    /** 机器编号 */
    @ApiModelProperty(value = "机器编号", position = 11, required = true)
    private String code;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 13, required = true)
    private String supplierId;

    /** 产品分类id */
    @ApiModelProperty(value = "产品分类id", position = 15, required = true)
    private String categoryId;

    /** 产品型号id */
    @ApiModelProperty(value = "产品型号id", position = 17, required = true)
    private String modelId;

    /** 机器价格 */
    @ApiModelProperty(value = "机器价格", position = 19)
    private BigDecimal price;

    /** 出厂日期 */
    @ApiModelProperty(value = "出厂日期", position = 21)
    private LocalDate manufactureDate;

    /** 产品有效期 */
    @ApiModelProperty(value = "产品有效期", position = 23)
    private LocalDate expiryDate;

    /** 供应商名称 */
    @ApiModelProperty(value = "供应商名称", position = 25)
    private String supplierName;

    /** 产品分类名 */
    @ApiModelProperty(value = "产品分类名", position = 27)
    private String categoryName;

    /** 产品型号 */
    @ApiModelProperty(value = "产品型号", position = 29)
    private String modelName;

}