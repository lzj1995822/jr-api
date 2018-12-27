package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountTO extends BaseTO {

    /** 客户id */
    private String customerId;

    /** 银行名称 */
    private String name;

    /** 银行账号 */
    private String account;

    /** 户名*/
    private String accountName;
}
