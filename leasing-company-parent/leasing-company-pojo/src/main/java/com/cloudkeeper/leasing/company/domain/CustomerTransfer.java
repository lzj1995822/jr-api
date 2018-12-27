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
 * 转移d的客户
 * @author asher
 */
@ApiModel(value = "转移d的客户", description = "转移d的客户")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_customer_transfer")
public class CustomerTransfer extends BaseEntity {

    /** 业务员转移记录id */
    @ApiModelProperty(value = "业务员转移记录id", position = 11, required = true)
    @Column(length = 36)
    private String transferId;

    /** 业务员转移记录 */
    @ApiModelProperty(value = "业务员转移记录")
    @ManyToOne
    @JoinColumn(name = "transferId", updatable = false, insertable = false)
    private BusinessTransfer businessTransfer;

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 13, required = true)
    @Column(length = 36)
    private String customerId;

    /** 客户 */
    @ApiModelProperty(value = "客户")
    @ManyToOne
    @JoinColumn(name = "customerId", updatable = false, insertable = false)
    private Customer customer;
}