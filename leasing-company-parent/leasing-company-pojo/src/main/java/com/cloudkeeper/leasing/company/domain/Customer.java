package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户
 *
 * @author jerry
 */
@Getter
@Setter
@Entity
@Table(name = "l_cp_customer")
public class Customer extends BaseEntity {

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

    /** 公司电话*/
    @ApiModelProperty(value = "公司电话", position = 19)
    @Column(length = 36)
    private String phoneNumber;

    /** 工厂地址--省*/
    @ApiModelProperty(value = "工厂地址--省", position = 21)
    @Column(length = 36)
    private String factoryProvince;

    /** 工厂地址--市*/
    @ApiModelProperty(value = "工厂地址--市", position = 23)
    @Column(length = 36)
    private String factoryCity;

    /** 工厂地址--详细地址*/
    @ApiModelProperty(value = "工厂地址--详细地址", position = 25)
    @Column(length = 200)
    private String factoryDetailAddress;

    /** 法人*/
    @ApiModelProperty(value = "法人", position = 27)
    @Transient
    private CompanyPersonnel corporation;

    /** 普通联系人*/
    @ApiModelProperty(value = "普通联系人", position = 29)
    @Transient
    private List<CompanyPersonnel> contactList;

    /** 股东、董事*/
    @ApiModelProperty(value = "股东、董事", position = 31)
    @Transient
    private List<CompanyPersonnel> shareholderList;

    /** 客户详细、开票资料等*/
    @ApiModelProperty(value = "客户详细、开票资料等", position = 33)
    @Transient
    private List<CustomerDetail> customerDetailList;

    /** 银行账户*/
    @ApiModelProperty(value = "银行账户", position = 35)
    @Transient
    private List<BankAccount> bankAccountList;

    public CustomerAllVO convert() {
        CustomerAllVO customerAllVO = new CustomerAllVO();
        BeanUtils.copyProperties(convert(CustomerVO.class), customerAllVO);
        BeanUtils.copyProperties(commonCompany, customerAllVO, "id");

        customerAllVO.setCorporation(corporation.convert(CompanyPersonnelVO.class));
        customerAllVO.setBankAccountList(BankAccount.convert(bankAccountList, BankAccountVO.class));
        customerAllVO.setCustomerDetailList(CustomerDetail.convert(customerDetailList, CustomerDetailVO.class));
        customerAllVO.setShareholderList(CompanyPersonnel.convert(shareholderList, CompanyPersonnelVO.class));
        customerAllVO.setContactList(CompanyPersonnel.convert(contactList, CompanyPersonnelVO.class));
        customerAllVO.setCustomerDetailDeleteList(new ArrayList<>());
        customerAllVO.setContactDeleteList(new ArrayList<>());
        customerAllVO.setShareholderDeleteList(new ArrayList<>());
        customerAllVO.setBankAccountDeleteList(new ArrayList<>());
        return customerAllVO;
    }

}
