package com.cloudkeeper.leasing.bean.company.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanyAllTO extends GuaranteeCompanyTO {

    /** 法人TO*/
    private CompanyPersonnelTO corporation;

    /** 联系人TOS*/
    private List<CompanyPersonnelTO> contactList;

    /** 股东董事TOS*/
    private List<CompanyPersonnelTO> shareholderList;

    /**名下资产TOS*/
    private List<SecuredAssetsTO> securedAssetsList;
}
