package com.cloudkeeper.leasing.company.dto.guaranteecompany;

import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanyDTO;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.vo.CompanyPersonnelVO;
import com.cloudkeeper.leasing.company.vo.SecuredAssetsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * 担保公司 DTO
 * @author asher
 */
@ApiModel(value = "担保公司 DTO", description = "担保公司 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanyDTO extends CommonCompanyDTO {

    /** 公司父表id */
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    private String parentId;

    /** 职工人数 */
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 登记地址*/
    @ApiModelProperty(value = "登记地址", position = 15)
    private String registeredAddress;

    /** 法人DTOs*/
    @ApiModelProperty(value = "法人DTOs", position = 17)
    private CompanyPersonnelDTO corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 19)
    private List<CompanyPersonnelDTO> contactList = new ArrayList<>();

    /** 股东董事DTOs*/
    @ApiModelProperty(value = "股东董事DTOs", position = 21)
    private List<CompanyPersonnelDTO> shareholderList = new ArrayList<>();

    /**名下资产DTOs*/
    @ApiModelProperty(value = "名下资产DTOs", position = 23)
    private List<SecuredAssetsDTO> securedAssetsList= new ArrayList<>();

    /** 名下资产需要被删除的ID集合*/
    @ApiModelProperty(value = "名下资产需要被删除的ID集合", position = 25)
    private List<String> securedAssetsDeleteList= new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 27)
    private List<String> contactDeleteList= new ArrayList<>();

    /** 股东董事需要被删除的ID集合*/
    @ApiModelProperty(value = "股东董事需要被删除的ID集合", position = 29)
    private List<String> shareholderDeleteList= new ArrayList<>();


}