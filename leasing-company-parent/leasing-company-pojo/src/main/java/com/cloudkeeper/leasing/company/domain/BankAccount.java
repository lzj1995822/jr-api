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
 * 银行账号
 * @author asher
 */
@ApiModel(value = "银行账号", description = "银行账号")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_bank_account")
public class BankAccount extends BaseEntity {

    /** 客户id*/
    @ApiModelProperty(value = "客户id", position = 11)
    @Column(length = 36)
    private String customerId;

    /** 客户*/
    @ApiModelProperty(value = "客户")
    @ManyToOne
    @JoinColumn(name = "customerId", insertable = false, updatable = false)
    private Customer customer;

    /** 银行名称*/
    @ApiModelProperty(value = "银行名称", position = 13)
    @Column(length = 36)
    private String name;

    /** 银行账号*/
    @ApiModelProperty(value = "银行账号", position = 15)
    @Column(length = 36)
    private String account;

    /** 户名*/
    @ApiModelProperty(value = "户名", position = 16)
    @Column(length = 36)
    private String accountName;

}