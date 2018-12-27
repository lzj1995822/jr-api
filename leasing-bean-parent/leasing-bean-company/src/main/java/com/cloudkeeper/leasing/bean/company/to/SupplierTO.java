package com.cloudkeeper.leasing.bean.company.to;

import lombok.Getter;
import lombok.Setter;

/**
 * 供应商 TO
 * @author asher
 */
@Getter
@Setter
public class SupplierTO extends CommonCompanyTO {

    /** 公司父表id */
    private String parentId;

    /** 供应商类别 */
    private String category;

    /** 全部回购 */
    private Integer repurchase;

    /** 是否可用合同章*/
    private Integer contractChapter;

    /** 是否密码管控*/
    private Integer cipherControl;

    /** 买卖合同是否适合补充条款*/
    private Integer applyAttachment;

    /** 是否可以网银汇款*/
    private Integer cyberBank;

}
