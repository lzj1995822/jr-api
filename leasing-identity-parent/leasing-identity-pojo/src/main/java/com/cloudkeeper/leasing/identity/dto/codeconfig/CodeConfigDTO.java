package com.cloudkeeper.leasing.identity.dto.codeconfig;

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
 * 编码配置 DTO
 * @author asher
 */
@ApiModel(value = "编码配置 DTO", description = "编码配置 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CodeConfigDTO extends BaseEditDTO {

    /** 业务类型 */
    @ApiModelProperty(value = "业务类型", position = 10, required = true)
    private String businessType;

    /** 是否需要加入分公司 */
    @ApiModelProperty(value = "是否需要加入分公司", position = 13)
    private Integer hasBranchCompany;

    /** 分公司 */
    @ApiModelProperty(value = "分公司", position = 15)
    private String branchCompany;

    /** 是否需要加入租赁类型 */
    @ApiModelProperty(value = "是否需要加入租赁类型", position = 17)
    private Integer hasLeasingType;

    /** 租赁类型 */
    @ApiModelProperty(value = "租赁类型", position = 19)
    private String leasingType;

    /** 是否需要加入日期 */
    @ApiModelProperty(value = "是否需要加入日期", position = 21)
    private Integer hasDate;

    /** 是否需要加入时间*/
    @ApiModelProperty(value = "是否需要加入时间", position = 21)
    private String dateTemplate;

    /** 是否需要加入分隔符 */
    @ApiModelProperty(value = "是否需要加入分隔符", position = 23)
    private Integer hasSeparator;

    /** 分隔符 */
    @ApiModelProperty(value = "分隔符", position = 25)
    private String codeSeparator;

    /** 流水号长度 */
    @ApiModelProperty(value = "流水号长度", position = 27)
    private Integer serialNumberLength;

    /** 流水号周期 */
    @ApiModelProperty(value = "流水号周期", position = 29)
    private String serialNumberCycle;

}