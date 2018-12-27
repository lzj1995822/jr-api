package com.cloudkeeper.leasing.company.dto.businesstransfer;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
import com.cloudkeeper.leasing.company.dto.customertransfer.CustomerTransferDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务员转移、转移客户主记录 DTO
 * @author asher
 */
@ApiModel(value = "业务员转移、转移客户主记录 DTO", description = "业务员转移、转移客户主记录 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTransferDTO extends BaseEditDTO {

    /** 原始客户经理id */
    @ApiModelProperty(value = "原始客户经理id", position = 11, required = true)
    private String originId;

    /** 目的客户经理id */
    @ApiModelProperty(value = "目的客户经理id", position = 13, required = true)
    private String newId;

    /** 移交原因 */
    @ApiModelProperty(value = "原因类型", position = 15)
    private String reasonType;

    /** 移交原因 */
    @ApiModelProperty(value = "移交原因", position = 15)
    private String reason;

    /** 被转移客户集合 */
    @ApiModelProperty(value = "被转移客户集合", position = 17)
    private List<CustomerTransferDTO> customerTransferDTOList = new ArrayList<>();

}