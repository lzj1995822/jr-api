package com.cloudkeeper.leasing.bean.company.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 *  公司担保 TO
 * @author louis
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanyTO extends CommonCompanyTO{

    /** 公司父表id */
    private String parentId;

    /** 职工人数 */
    private Integer staffNumber;

    /** 登记地址*/
    private String registeredAddress;
}
