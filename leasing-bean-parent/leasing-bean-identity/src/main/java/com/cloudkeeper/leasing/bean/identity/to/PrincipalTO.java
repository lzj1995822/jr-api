package com.cloudkeeper.leasing.bean.identity.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 用户 TO
 * @author jerry
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalTO extends BaseTO {

    /** 登录名*/
    private String code;

    /** 姓名*/
    private String name;

    /** 主管*/
    private String director;

    /** 分公司*/
    private String branch;

    /** 部门id*/
    private String deptId;

    /** 分公司id*/
    private String branchId;
}
