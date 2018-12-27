package com.cloudkeeper.leasing.company.dto.supplier;

import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanyDTO;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商 DTO
 * @author asher
 */
@ApiModel(value = "供应商 DTO", description = "供应商 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO extends CommonCompanyDTO {

    /** 公司父表id */
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    private String parentId;

    /** 供应商类别 */
    @ApiModelProperty(value = "供应商类别", position = 13, required = true)
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
    private CompanyPersonnelDTO corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 25)
    private List<CompanyPersonnelDTO> contactList = new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 27)
    private List<String> contactDeleteList = new ArrayList<>();

}