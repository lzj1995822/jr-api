package com.cloudkeeper.leasing.company.dto.securedassets;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
import com.cloudkeeper.leasing.base.dto.BaseUpdateDTO;
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
 * 担保资产 DTO
 * @author asher
 */
@ApiModel(value = "担保资产 DTO", description = "担保资产 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SecuredAssetsDTO extends BaseUpdateDTO {

    /** 担保对象id */
    @ApiModelProperty(value = "担保对象id", position = 11, required = true)
    private String guarantorId;

    /** 资产分类 */
    @ApiModelProperty(value = "资产分类", position = 13)
    private String category;

    /** 所有权人 */
    @ApiModelProperty(value = "所有权人", position = 15)
    private String owner;

    /** 有无抵押 */
    @ApiModelProperty(value = "有无抵押", position = 17)
    private Integer mortgage;

    /** 地址 */
    @ApiModelProperty(value = "地址", position = 19)
    private String address;

    /** 面积 */
    @ApiModelProperty(value = "面积", position = 21)
    private Double area;

    /** 权证号 */
    @ApiModelProperty(value = "权证号", position = 23)
    private String warrantNumber;

    /** 建造时间 */
    @ApiModelProperty(value = "建造时间", position = 25)
    private LocalDate constructionDate;

    /** 市场价格 */
    @ApiModelProperty(value = "市场价格", position = 27)
    private BigDecimal marketPrice;

    /** 设定情形 */
    @ApiModelProperty(value = "设定情形", position = 29)
    private String situation;

}