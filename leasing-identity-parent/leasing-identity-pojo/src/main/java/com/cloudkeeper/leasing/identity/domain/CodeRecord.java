package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 编码生成记录
 * @author asher
 */
@ApiModel(value = "编码生成记录", description = "编码生成记录")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ck_id_code_record")
public class CodeRecord extends BaseEntity {

    /** 业务类型 */
    @ApiModelProperty(value = "业务类型", position = 10, required = true)
    @Column(length = 36)
    private String businessType;

    /** 所用配置id */
    @ApiModelProperty(value = "所用配置id", position = 11, required = true)
    @Column(length = 36)
    private String configId;

    /** 所用配置id */
    @ApiModelProperty(value = "所用配置", position = 11, required = true)
    @ManyToOne
    @JoinColumn(name = "configId", updatable = false, insertable = false)
    private CodeConfig codeConfig;

    /** 分公司 */
    @ApiModelProperty(value = "分公司", position = 12)
    @Column(length = 36)
    private String branchCompany;

    /** 租赁类型 */
    @ApiModelProperty(value = "租赁类型", position = 14)
    @Column(length = 36)
    private String leasingType;

    /** 日期 */
    @ApiModelProperty(value = "日期", position = 16)
    private LocalDateTime date;

    /** 分隔符 */
    @ApiModelProperty(value = "分隔符", position = 18)
    @Column(length = 5)
    private String codeSeparator;

    /** 流水号 */
    @ApiModelProperty(value = "流水号", position = 20)
    @Column(length = 36)
    private String serialNumber;

}