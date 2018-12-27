package com.cloudkeeper.leasing.identity.vo;

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
 * 编码生成记录 VO
 * @author asher
 */
@ApiModel(value = "编码生成记录 VO", description = "编码生成记录 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CodeRecordVO extends BaseVO {

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