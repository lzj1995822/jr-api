package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 担保公司、法人、联系人、股东董事、名下资产 VO
 * @author asher
 */
@ApiModel(value = "担保公司、法人、联系人、股东董事、名下资产 VO", description = "担保公司、法人、联系人、股东董事、名下资产 VO")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanyAllVO extends GuaranteeCompanyVO{

    /** 客户经理*/
    @ApiModelProperty(value = "客户经理",position = 16)
    private String customerManager;

    /** 客户、担保公司、供应商父表 VO*/
    @ApiModelProperty(value = "客户、担保公司、供应商父表 VO", position = 17)
    private CommonCompanyVO commonCompany;

    /** 法人VOS*/
    @ApiModelProperty(value = "法人VOS", position = 19)
    private CompanyPersonnelVO corporation;

    /** 联系人VOS*/
    @ApiModelProperty(value = "联系人VOS", position = 21)
    private List<CompanyPersonnelVO> contactList = new ArrayList<>();

    /** 股东董事VOS*/
    @ApiModelProperty(value = "股东董事VOS", position = 21)
    private List<CompanyPersonnelVO> shareholderList = new ArrayList<>();

    /**名下资产VOS*/
    @ApiModelProperty(value = "名下资产VOS", position = 23)
    private List<SecuredAssetsVO> securedAssetsList = new ArrayList<>();

}
