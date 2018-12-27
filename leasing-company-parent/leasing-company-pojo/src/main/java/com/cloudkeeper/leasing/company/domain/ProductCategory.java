package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.ProductCategoryVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * 产品分类
 *
 * @author asher
 */
@ApiModel(value = "产品分类", description = "产品分类")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_product_category")
public class ProductCategory extends BaseEntity {

    /**
     * 类别名字
     */
    @ApiModelProperty(value = "类别名字", position = 21, required = true)
    @Column(length = 36)
    private String name;

    /**
     * 供应商id
     */
    @ApiModelProperty(value = "供应商id", position = 23, required = true)
    @Column(length = 36)
    private String supplierId;

    /**
     * 供应商
     */
    @ApiModelProperty(value = "供应商")
    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    private Supplier supplier;

    /**厂牌*/
    @ApiModelProperty(value = "厂牌", position = 27)
    @Column(length = 36)
    private String brand;

    /** 产地类型*/
    @ApiModelProperty(value = "产地类型", position = 29)
    @Column(length = 36)
    private String originType;
    
    public ProductCategoryVO convert() {
        ProductCategoryVO convert = super.convert(ProductCategoryVO.class);
        convert.setSupplierName(supplier.getCommonCompany().getName());
        return convert;
    }
}