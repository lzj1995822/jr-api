package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.CompanyPersonnelVO;
import com.cloudkeeper.leasing.company.vo.SupplierAllVO;
import com.cloudkeeper.leasing.company.vo.SupplierVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商
 * @author asher
 */
@ApiModel(value = "供应商", description = "供应商")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_supplier")
public class Supplier extends BaseEntity {

    /** 公司父表id*/
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    @Column(length = 36)
    private String parentId;

    /** 公司父表对象*/
    @ApiModelProperty(value = "公司父表对象", position = 12)
    @OneToOne
    @JoinColumn(name = "parentId", updatable = false, insertable = false)
    private CommonCompany commonCompany;

    /** 供应商类别 */
    @ApiModelProperty(value = "供应商类别", position = 13, required = true)
    @Column(length = 60)
    private String category;

    /** 全部回购 */
    @ApiModelProperty(value = "全部回购", position = 15)
    private Integer repurchase;

    /** 是否可用合同章*/
    @ApiModelProperty(value = "是否可用合同章", position = 17)
    private Integer contractChapter;

    /** 是否密码管控*/
    @ApiModelProperty(value = "是否密码管控", position = 19)
    private Integer cipherControl;

    /** 买卖合同是否适合补充条款*/
    @ApiModelProperty(value = "买卖合同是否适合补充条款", position = 21)
    private Integer applyAttachment;

    /** 是否可以网银汇款*/
    @ApiModelProperty(value = "是否可以网银汇款", position = 23)
    private Integer cyberBank;

    /** 法人*/
    @ApiModelProperty(value = "法人", position = 24)
    @Transient
    private CompanyPersonnel corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 25)
    @Transient
    private List<CompanyPersonnel> contactList = new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 27)
    @Transient
    private List<String> contactDeleteList = new ArrayList<>();

    public SupplierAllVO convert() {
        SupplierAllVO supplierAllVO = new SupplierAllVO();
        BeanUtils.copyProperties(convert(SupplierVO.class), supplierAllVO);
        BeanUtils.copyProperties(commonCompany, supplierAllVO, "id");

        supplierAllVO.setCorporation(corporation.convert(CompanyPersonnelVO.class));
        supplierAllVO.setContactList(CompanyPersonnel.convert(contactList, CompanyPersonnelVO.class));
        supplierAllVO.setContactDeleteList(new ArrayList<>());
        return supplierAllVO;
    }

}