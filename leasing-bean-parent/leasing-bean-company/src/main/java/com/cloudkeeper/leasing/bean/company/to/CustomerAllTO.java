package com.cloudkeeper.leasing.bean.company.to;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerAllTO extends CustomerTO {

    /** 法人DTOs*/
    private CompanyPersonnelTO corporation;

    /** 联系人DTOs*/
    private List<CompanyPersonnelTO> contactList;

    /** 股东董事DTOs*/
    private List<CompanyPersonnelTO> shareholderList;

    /** 客户详细、开票资料DTO*/
    private List<CustomerDetailTO> customerDetailList;

    /** 银行账号DTO*/
    private List<BankAccountTO> bankAccountList;

    /** 联系人需要被删除的ID集合*/
    private List<String> contactDeleteList;

    /** 股东董事需要被删除的ID集合*/
    private List<String> shareholderDeleteList;

    /** 客户详细、开票资料需要被删除的ID集合*/
    private List<String> customerDetailDeleteList;

    /** 银行账号需要被删除的ID集合*/
    private List<String> bankAccountDeleteList;
}
