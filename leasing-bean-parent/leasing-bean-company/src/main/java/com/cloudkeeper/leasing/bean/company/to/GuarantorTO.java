package com.cloudkeeper.leasing.bean.company.to;

import com.cloudkeeper.leasing.bean.base.to.BaseTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

/**
 * 担保人T O
 * @author  louis
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorTO extends BaseTO{

    /** 担保人姓名 */
    private String name;

    /** 证件类型 */
    private String certificateType;

    /** 身份证号 */
    private String identity;

    /** 出生日期 */
    private LocalDate birthday;

    /** 性别（0女男1） */
    private Integer sex;

    /** 单位地址 */
    private String companyAddress;

    /** 电话 */
    private String phone;

    /** 家庭地址 */
    private String homeAddress;

    /** 工作单位 */
    private String companyName;

    /** 联系人姓名 */
    private String contactName;

    /** 联系人电话 */
    private String contactPhone;

    /** 联系人地址 */
    private String contactAddress;

    /** 历史主键id*/
    private String pkId;

    /** 版本*/
    private Integer version;
}
