package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 名下资产 TO
 * @author louis
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SecuredAssetsTO extends BaseTO{

    /** 担保对象id */
    private String guarantorId;

    /** 资产分类 */
    private String category;

    /** 所有权人 */
    private String owner;

    /** 有无抵押 */
    private Integer mortgage;

    /** 地址 */
    private String address;

    /** 面积 */
    private Double area;

    /** 权证号 */
    private String warrantNumber;

    /** 建造时间 */
    private LocalDate constructionDate;

    /** 市场价格 */
    private BigDecimal marketPrice;

    /** 设定情形 */
    private String situation;
}
