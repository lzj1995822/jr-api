package com.cloudkeeper.leasing.company.dto.customer;

import com.cloudkeeper.leasing.company.dto.bankaccount.BankAccountDTO;
import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanyDTO;
import com.cloudkeeper.leasing.company.dto.companypersonnel.CompanyPersonnelDTO;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户dto
 * @author jerry
 */
@ApiModel(value = "客户dto", description = "客户dto")
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO extends CommonCompanyDTO {

    /** 公司父表id*/
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    @Length(max = 36)
    private String parentId;

    /** 职工人数*/
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 公司电话*/
    @ApiModelProperty(value = "公司电话", position = 19)
    @Length(max = 36)
    private String phoneNumber;

    /** 工厂地址--省*/
    @ApiModelProperty(value = "工厂地址--省", position = 21)
    @Length(max = 36)
    private String factoryProvince;

    /** 工厂地址--市*/
    @ApiModelProperty(value = "工厂地址--市", position = 23)
    @Length(max = 36)
    private String factoryCity;

    /** 工厂地址--详细地址*/
    @ApiModelProperty(value = "工厂地址--详细地址", position = 25)
    @Length(max = 200)
    private String factoryDetailAddress;

    /** 法人DTOs*/
    @ApiModelProperty(value = "法人DTOs", position = 27)
    private CompanyPersonnelDTO corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 29)
    private List<CompanyPersonnelDTO> contactList = new ArrayList<>();

    /** 股东董事DTOs*/
    @ApiModelProperty(value = "股东董事DTOs", position = 31)
    private List<CompanyPersonnelDTO> shareholderList = new ArrayList<>();

    /** 客户详细、开票资料DTO*/
    @ApiModelProperty(value = "客户详细、开票资料DTO", position = 33)
    private List<CustomerDetailDTO> customerDetailList = new ArrayList<>();

    /** 银行账号DTO*/
    @ApiModelProperty(value = "银行账号DTO", position = 35)
    private List<BankAccountDTO> bankAccountList = new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 37)
    private List<String> contactDeleteList = new ArrayList<>();

    /** 股东董事需要被删除的ID集合*/
    @ApiModelProperty(value = "股东董事需要被删除的ID集合", position = 39)
    private List<String> shareholderDeleteList = new ArrayList<>();

    /** 客户详细、开票资料需要被删除的ID集合*/
    @ApiModelProperty(value = "** 客户详细、开票资料需要被删除的ID集合", position = 41)
    private List<String> customerDetailDeleteList = new ArrayList<>();

    /** 银行账号需要被删除的ID集合*/
    @ApiModelProperty(value = "银行账号需要被删除的ID集合", position = 43)
    private List<String> bankAccountDeleteList = new ArrayList<>();

}
