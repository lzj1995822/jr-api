package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
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
 * 转移d的客户 VO
 * @author asher
 */
@ApiModel(value = "转移d的客户 VO", description = "转移d的客户 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransferVO extends BaseVO {

    /** 业务员转移记录id */
    @ApiModelProperty(value = "业务员转移记录id", position = 11, required = true)
    private String transferId;

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 13, required = true)
    private String customerId;

}