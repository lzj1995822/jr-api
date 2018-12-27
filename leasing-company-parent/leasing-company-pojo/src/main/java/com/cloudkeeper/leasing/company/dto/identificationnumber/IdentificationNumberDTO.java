package com.cloudkeeper.leasing.company.dto.identificationnumber;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
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
 * 机器编号 DTO
 * @author asher
 */
@ApiModel(value = "机器编号 DTO", description = "机器编号 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class IdentificationNumberDTO extends BaseEditDTO {

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

}