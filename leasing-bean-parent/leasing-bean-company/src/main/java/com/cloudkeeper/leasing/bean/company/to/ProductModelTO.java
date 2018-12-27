package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 产品型号 VO
 * @author asher
 */
@Getter
@Setter
public class ProductModelTO extends BaseTO {

    /** 型号名字 */
    private String name;

    /** 供应商id */
    private String supplierId;

    /** 产品分类id */
    private String categoryId;

    /** 使用年限 */
    private Integer usefulLife;

    /** 单位 */
    private String unit;

    /** 原始价格 */
    private BigDecimal originalPrice;

    /** 账面价格 */
    private BigDecimal bookPrice;

    /** 币别 */
    private String currency;

    /**是否支持锁码*/
    private Integer supportCode;

    /** 产品型号ID */
    private String pkId;
}
