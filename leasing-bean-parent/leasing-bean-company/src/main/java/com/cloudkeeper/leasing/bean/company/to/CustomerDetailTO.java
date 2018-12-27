package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailTO extends BaseTO {

    /** 客户id */
    private String customerId;

    /** 纳税人 */
    private String taxpayer;

    /** 统一识别号 */
    private String taxpayerIdentity;

    /** 地址 */
    private String address;

    /** 联系电话 */
    private String phone;

    /** 开户行名称 */
    private String bankName;

    /** 是否为增值税一般纳税人 */
    private Integer general;

    /** 是否需要开具增值税专用发票 */
    private Integer specialInvoice;

    /** 发票寄送人 */
    private String sent;
}
