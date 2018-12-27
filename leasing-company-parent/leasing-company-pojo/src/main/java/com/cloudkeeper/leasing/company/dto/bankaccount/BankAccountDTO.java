package com.cloudkeeper.leasing.company.dto.bankaccount;

import com.cloudkeeper.leasing.base.dto.BaseUpdateDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 银行账号 DTO
 * @author asher
 */
@ApiModel(value = "银行账号 DTO", description = "银行账号 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO extends BaseUpdateDTO {

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 11)
    private String customerId;

    /** 银行名称 */
    @ApiModelProperty(value = "银行名称", position = 13)
    private String name;

    /** 银行账号 */
    @ApiModelProperty(value = "银行账号", position = 15)
    private String account;

    /** 户名*/
    @ApiModelProperty(value = "户名", position = 16)
    private String accountName;

}