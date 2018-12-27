package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.ProductModelAllVO;
import com.cloudkeeper.leasing.company.vo.ProductModelVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 产品型号
 * @author asher
 */
@ApiModel(value = "产品型号", description = "产品型号")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_product_model")
public class ProductModel extends BaseEntity {

    /** 型号名字 */
    @ApiModelProperty(value = "型号名字", position = 21, required = true)
    @Column(length = 36)
    private String name;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 23, required = true)
    @Column(length = 36)
    private String supplierId;

    /** 供应商 */
    @ApiModelProperty(value = "供应商")
    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    private Supplier supplier;

    /** 产品分类id */
    @ApiModelProperty(value = "产品分类id", position = 25, required = true)
    @Column(length = 36)
    private String categoryId;

    /** 产品分类 */
    @ApiModelProperty(value = "产品分类")
    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private ProductCategory productCategory;

    /** 使用年限 */
    @ApiModelProperty(value = "使用年限", position = 27)
    private Integer usefulLife;

    /** 单位 */
    @ApiModelProperty(value = "单位", position = 29)
    @Column(length = 36)
    private String unit;

    /** 原始价格 */
    @ApiModelProperty(value = "原始价格", position = 31)
    private BigDecimal originalPrice;

    /** 账面价格 */
    @ApiModelProperty(value = "账面价格", position = 33)
    private BigDecimal bookPrice;

    /** 币别 */
    @ApiModelProperty(value = "币别", position = 35)
    @Column(length = 36)
    private String currency;

    /**是否支持锁码*/
    @ApiModelProperty(value = "是否支持锁码", position = 37)
    private Integer supportCode;

    public ProductModelAllVO convert() {
        ProductModelVO convert = super.convert(ProductModelVO.class);
        ProductModelAllVO productModelAllVO = new ProductModelAllVO();
        BeanUtils.copyProperties(convert, productModelAllVO);
        productModelAllVO.setSupplierName(supplier.getCommonCompany().getName());
        productModelAllVO.setCategoryName(productCategory.getName());
        return productModelAllVO;
    }
}