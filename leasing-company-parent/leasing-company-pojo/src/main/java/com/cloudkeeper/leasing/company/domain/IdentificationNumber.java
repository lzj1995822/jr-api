package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.IdentificationNumberVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 机器编号
 * @author asher
 */
@ApiModel(value = "机器编号", description = "机器编号")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_identification_number")
public class IdentificationNumber extends BaseEntity {

    /** 型号名字 */
    @ApiModelProperty(value = "机器编号", position = 11, required = true)
    @Column(length = 36)
    private String code;

    /** 供应商id */
    @ApiModelProperty(value = "供应商id", position = 13, required = true)
    @Column(length = 36)
    private String supplierId;

    /** 供应商 */
    @ApiModelProperty(value = "供应商")
    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    private Supplier supplier;

    /** 产品分类id */
    @ApiModelProperty(value = "产品分类id", position = 15, required = true)
    @Column(length = 36)
    private String categoryId;

    /** 产品分类 */
    @ApiModelProperty(value = "产品分类")
    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private ProductCategory productCategory;

    /** 产品型号id */
    @ApiModelProperty(value = "产品型号id", position = 17, required = true)
    @Column(length = 36)
    private String modelId;

    /** 产品型号*/
    @ApiModelProperty(value = "产品型号")
    @ManyToOne
    @JoinColumn(name = "modelId", insertable = false, updatable = false)
    private ProductModel productModel;

    /** 机器价格 */
    @ApiModelProperty(value = "机器价格", position = 19)
    private BigDecimal price;

    /** 出厂日期 */
    @ApiModelProperty(value = "出厂日期", position = 21)
    private LocalDate manufactureDate;

    /** 产品有效期 */
    @ApiModelProperty(value = "产品有效期", position = 23)
    private LocalDate expiryDate;

    public IdentificationNumberVO convert() {
        IdentificationNumberVO convert = super.convert(IdentificationNumberVO.class);
        convert.setSupplierName(supplier.getCommonCompany().getName());
        convert.setCategoryName(productCategory.getName());
        convert.setModelName(productModel.getName());
        return convert;
    }
}