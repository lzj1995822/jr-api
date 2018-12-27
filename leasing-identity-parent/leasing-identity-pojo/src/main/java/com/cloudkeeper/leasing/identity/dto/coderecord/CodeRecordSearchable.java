package com.cloudkeeper.leasing.identity.dto.coderecord;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
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
 * 编码生成记录 查询DTO
 * @author asher
 */
@ApiModel(value = "编码生成记录 查询DTO", description = "编码生成记录 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CodeRecordSearchable extends BaseSearchable {

    /** 业务类型 */
    @ApiModelProperty(value = "业务类型", position = 10, required = true)
    private String businessType;

    /** 所用配置id */
    @ApiModelProperty(value = "所用配置id", position = 11, required = true)
    private String configId;

    /** 分公司 */
    @ApiModelProperty(value = "分公司", position = 12)
    private String branchCompany;

    /** 租赁类型 */
    @ApiModelProperty(value = "租赁类型", position = 14)
    private String leasingType;

    /** 日期 */
    @ApiModelProperty(value = "日期", position = 16)
    private LocalDate date;

    /** 分隔符 */
    @ApiModelProperty(value = "分隔符", position = 18)
    private String codeSeparator;

    /** 流水号 */
    @ApiModelProperty(value = "流水号", position = 20)
    private String serialNumber;

}