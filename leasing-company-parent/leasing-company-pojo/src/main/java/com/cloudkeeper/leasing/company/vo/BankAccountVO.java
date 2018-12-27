package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 银行账号 VO
 * @author asher
 */
@ApiModel(value = "银行账号 VO", description = "银行账号 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountVO extends BaseVO {

    /** 客户id */
    @ApiModelProperty(value = "客户id", position = 21)
    private String customerId;

    /** 银行名称 */
    @ApiModelProperty(value = "银行名称", position = 23)
    private String name;

    /** 银行账号 */
    @ApiModelProperty(value = "银行账号", position = 25)
    private String account;

    /** 户名*/
    @ApiModelProperty(value = "户名", position = 27)
    private String accountName;
}