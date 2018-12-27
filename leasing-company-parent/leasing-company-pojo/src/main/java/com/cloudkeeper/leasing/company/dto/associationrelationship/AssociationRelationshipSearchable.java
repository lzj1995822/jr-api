package com.cloudkeeper.leasing.company.dto.associationrelationship;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 关联关系 查询DTO
 * @author asher
 */
@ApiModel(value = "关联关系 查询DTO", description = "关联关系 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AssociationRelationshipSearchable extends BaseSearchable {

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 11)
    private String customerId;

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

    /** 客户名称*/
    @ApiModelProperty(value = "客户名称", position = 27)
    private String customerName;

    /** 关联客户名称*/
    @ApiModelProperty(value = "关联客户名称", position = 29)
    private String associateName;

}