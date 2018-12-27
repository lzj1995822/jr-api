package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyPersonnelTO extends BaseTO {

    /** 姓名 */
    private String name;

    /** 关联公司的id */
    private String companyId;

    /** 身份证号 */
    private String identity;

    /** 办公电话 */
    private String officeNumber;

    /** 手机号 */
    private String mobilePhone;

    /** 常住地址 */
    private String address;

    /** 电子邮箱 */
    private String email;

    /** 传真 */
    private String fax;

    /** 邮编 */
    private String post;

    /** 职务 */
    private String position;

    /** 邮寄地址 */
    private String postAddress;

    /** 股东类型（股东、董事） */
    private String shareholderType;

    /** 出资情况 */
    private String capitalContribution;

    /** 出资方式 */
    private String contributionMethod;

    /** 股权比例 */
    private Double shareRatio;

    /** 备注 */
    private String note;

    /** 负责人类型 */
    private String type;

    /** 证件类型 */
    private String credentialType;

    /** 币别 */
    private String currency;

    /** 与负责人关系 */
    private String relationship;
}
