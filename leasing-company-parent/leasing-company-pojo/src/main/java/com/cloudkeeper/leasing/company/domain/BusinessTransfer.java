package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.BusinessTransferVO;
import com.cloudkeeper.leasing.company.vo.CustomerTransferVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务员转移、转移客户主记录
 * @author asher
 */
@ApiModel(value = "业务员转移、转移客户主记录", description = "业务员转移、转移客户主记录")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_business_transfer")
public class BusinessTransfer extends BaseEntity {

    /** 原始客户经理id */
    @ApiModelProperty(value = "原始客户经理id", position = 11, required = true)
    @Column(length = 36)
    private String originId;

    /** 目的客户经理id */
    @ApiModelProperty(value = "目的客户经理id", position = 13, required = true)
    @Column(length = 36)
    private String newId;

    /** 移交原因 */
    @ApiModelProperty(value = "原因类型", position = 15)
    @Column(length = 36)
    private String reasonType;

    /** 移交原因 */
    @ApiModelProperty(value = "移交原因", position = 15)
    @Column(length = 36)
    private String reason;

    /** 被转移客户集合 */
    @ApiModelProperty(value = "被转移客户集合", position = 17)
    @OneToMany(mappedBy = "businessTransfer")
    private List<CustomerTransfer> customerTransferList = new ArrayList<>();

    public BusinessTransferVO convert() {
        BusinessTransferVO businessTransferVO = super.convert(BusinessTransferVO.class);
        businessTransferVO.setCustomerTransferVOList(CustomerTransfer.convert(customerTransferList, CustomerTransferVO.class));
        return businessTransferVO;
    }
}