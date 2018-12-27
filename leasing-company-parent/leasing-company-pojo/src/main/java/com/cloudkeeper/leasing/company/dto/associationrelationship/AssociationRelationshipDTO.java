package com.cloudkeeper.leasing.company.dto.associationrelationship;

import com.cloudkeeper.leasing.base.dto.BaseUpdateDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 关联关系 DTO
 * @author asher
 */
@ApiModel(value = "关联关系 DTO", description = "关联关系 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AssociationRelationshipDTO extends BaseUpdateDTO {

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 11)
    private String customerId;

    /** 关联客户id*/
    @ApiModelProperty(value = "关联客户id", position = 12)
    private String associateId;

    /** 关系类型 */
    @ApiModelProperty(value = "关系类型", position = 13)
    private String type;

    /** 关联类别 */
    @ApiModelProperty(value = "关联类别", position = 15)
    private String category;

    /** 投放额度 */
    @ApiModelProperty(value = "投放额度", position = 17)
    private BigDecimal quota;

    /** 已投放金额 */
    @ApiModelProperty(value = "已投放金额", position = 19)
    private BigDecimal invested;

    /** 剩余额度 */
    @ApiModelProperty(value = "剩余额度", position = 21)
    private BigDecimal rest;

    /** 未回款金额 */
    @ApiModelProperty(value = "未回款金额", position = 23)
    private BigDecimal noReturned;

    /** 逾期金额 */
    @ApiModelProperty(value = "逾期金额", position = 25)
    private BigDecimal overdue;

    /** 设立目的*/
    @ApiModelProperty(value = "设立目的", position = 27)
    private String purpose;

    /** 股权比例*/
    @ApiModelProperty(value = "股权比例", position = 29)
    private Double ratio;
}