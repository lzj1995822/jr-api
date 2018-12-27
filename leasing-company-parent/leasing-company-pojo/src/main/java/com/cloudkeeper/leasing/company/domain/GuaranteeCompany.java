package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * 担保公司
 * @author asher
 */
@ApiModel(value = "担保公司", description = "担保公司")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_guarantee_company")
public class GuaranteeCompany extends BaseEntity {

    /** 公司父表id*/
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    @Column(length = 36)
    private String parentId;

    /** 公司父表对象*/
    @ApiModelProperty(value = "公司父表对象", position = 12)
    @OneToOne
    @JoinColumn(name = "parentId", updatable = false, insertable = false)
    private CommonCompany commonCompany;

    /** 职工人数*/
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 登记地址*/
    @ApiModelProperty(value = "登记地址", position = 15)
    @Column(length = 100)
    private String registeredAddress;

    /** 法人*/
    @ApiModelProperty(value = "法人", position = 17)
    @Transient
    private CompanyPersonnel corporation;

    /** 普通联系人*/
    @ApiModelProperty(value = "普通联系人", position = 19)
    @Transient
    private List<CompanyPersonnel> contactList;

    /** 股东、董事*/
    @ApiModelProperty(value = "股东、董事", position = 21)
    @Transient
    private List<CompanyPersonnel> shareholderList;

    /** 担保资产*/
    @ApiModelProperty(value = "担保资产", position = 23)
    @Transient
    private List<SecuredAssets> assetsList;

    /** 当前版本下的历史id*/
    @ApiModelProperty(value = "当前版本下的历史id", position = 25)
    @Transient
    private String pkId;

    /**
     * 显示（法人、股东、普通联系人、担保资产）
     * @return vo
     */
    public GuaranteeCompanyAllVO convert() {
        GuaranteeCompanyAllVO guaranteeAllVO = convert(GuaranteeCompanyAllVO.class);

        guaranteeAllVO.setCommonCompany(commonCompany.convert(CommonCompanyVO.class));
        guaranteeAllVO.setCorporation(corporation.convert(CompanyPersonnelVO.class));
        guaranteeAllVO.setShareholderList(CompanyPersonnel.convert(shareholderList, CompanyPersonnelVO.class));
        guaranteeAllVO.setContactList(CompanyPersonnel.convert(contactList, CompanyPersonnelVO.class));
        guaranteeAllVO.setSecuredAssetsList(SecuredAssets.convert(assetsList, SecuredAssetsVO.class));
        return guaranteeAllVO;
    }


}