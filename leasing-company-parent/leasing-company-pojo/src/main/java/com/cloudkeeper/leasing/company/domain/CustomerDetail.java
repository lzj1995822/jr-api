package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 客户详细、开票资料
 * @author asher
 */
@ApiModel(value = "客户详细、开票资料", description = "客户详细、开票资料")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_customer_detail")
public class CustomerDetail extends BaseEntity {

    /** 客户id*/
    @ApiModelProperty(value = "客户id", position = 11, required = true)
    @Column(length = 36)
    private String customerId;

    /** 客户*/
    @ApiModelProperty(value = "客户")
    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;

    /** 纳税人*/
    @ApiModelProperty(value = "纳税人", position = 13)
    @Column(length = 36)
    private String taxpayer;

    /** 统一识别号*/
    @ApiModelProperty(value = "统一识别号", position = 15)
    @Column(length = 36)
    private String taxpayerIdentity;

    /** 地址*/
    @ApiModelProperty(value = "地址", position = 17)
    @Column(length = 36)
    private String address;

    /** 联系电话*/
    @ApiModelProperty(value = "联系电话", position = 19)
    @Column(length = 36)
    private String phone;

    /** 开户行名称*/
    @ApiModelProperty(value = "开户行名称", position = 21)
    @Column(length = 36)
    private String bankName;

    /** 是否为增值税一般纳税人*/
    @ApiModelProperty(value = "是否为增值税一般纳税人", position = 23)
    private Integer general;

    /** 是否需要开具增值税专用发票*/
    @ApiModelProperty(value = "是否需要开具增值税专用发票", position = 25)
    private Integer specialInvoice;

    /** 发票寄送人*/
    @ApiModelProperty(value = "发票寄送人", position = 27)
    @Column(length = 36)
    private String sent;

}