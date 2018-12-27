package com.cloudkeeper.leasing.bean.company.to;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商 ALLTO
 * @author asher
 */
@Getter
@Setter
public class SupplierAllTO extends SupplierTO {

    /** 法人*/
    private CompanyPersonnelTO corporation;

    /** 联系人DTOs*/
    private List<CompanyPersonnelTO> contactList = new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    private List<String> contactDeleteList = new ArrayList<>();
}
