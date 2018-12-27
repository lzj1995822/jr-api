package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.AssociationRelationshipVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 关联关系
 *
 * @author asher
 */
@ApiModel(value = "关联关系", description = "关联关系")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_association_relationship")
public class AssociationRelationship extends BaseEntity {

    /** 客户id*/
    @ApiModelProperty(value = "客户id", position = 11)
    @Column(length = 36)
    private String customerId;

    /** 客户*/
    @ApiModelProperty(value = "客户")
    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;

    /** 关联客户id*/
    @ApiModelProperty(value = "客户id", position = 12)
    @Column(length = 36)
    private String associateId;

    @ApiModelProperty(value = "关联客户")
    @ManyToOne
    @JoinColumn(name = "associateId", insertable = false, updatable = false)
    private Customer associate;

    /** 关系类型 */
    @ApiModelProperty(value = "关系类型", position = 13)
    @Column(length = 36)
    private String type;

    /** 关联类别*/
    @ApiModelProperty(value = "关联类别", position = 15)
    @Column(length = 36)
    private String category;

    /** 投放额度*/
    @ApiModelProperty(value = "投放额度", position = 17)
    private BigDecimal quota;

    /** 已投放金额*/
    @ApiModelProperty(value = "已投放金额", position = 19)
    private BigDecimal invested;

    /** 剩余额度*/
    @ApiModelProperty(value = "剩余额度", position = 21)
    private BigDecimal rest;

    /** 未回款金额*/
    @ApiModelProperty(value = "未回款金额", position = 23)
    private BigDecimal noReturned;

    /** 逾期金额*/
    @ApiModelProperty(value = "逾期金额", position = 25)
    private BigDecimal overdue;

    /** 设立目的*/
    @ApiModelProperty(value = "设立目的", position = 27)
    private String purpose;

    /** 股权比例*/
    @ApiModelProperty(value = "股权比例", position = 29)
    private Double ratio;

    @Nonnull
    public AssociationRelationshipVO convert() {
        AssociationRelationshipVO convert = super.convert(AssociationRelationshipVO.class);
        convert.setCustomerName(customer.getCommonCompany().getName());
        convert.setAssociateName(associate.getCommonCompany().getName());
        return convert;
    }
}