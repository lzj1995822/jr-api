package com.cloudkeeper.leasing.company.dto.customertransfer;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
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
 * 转移d的客户 查询DTO
 * @author asher
 */
@ApiModel(value = "转移d的客户 查询DTO", description = "转移d的客户 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransferSearchable extends BaseSearchable {

    /** 业务员转移记录id */
    @ApiModelProperty(value = "业务员转移记录id", position = 11, required = true)
    private String transferId;

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 13, required = true)
    private String customerId;

}