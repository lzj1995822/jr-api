package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryTO extends BaseTO {

    /** 类别名字*/
    private String name;

    /** 供应商id*/
    private String supplierId;

    /**厂牌*/
    private String brand;

    /** 产地类型*/
    private String originType;

    /** 产品分类历史ID */
    private String pkId;
}
