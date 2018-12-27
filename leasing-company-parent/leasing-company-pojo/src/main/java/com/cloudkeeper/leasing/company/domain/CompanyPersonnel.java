package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 公司相关人员
 *
 * @author asher
 */
@ApiModel(value = "公司相关人员", description = "公司相关人员")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_company_personnel")
public class CompanyPersonnel extends BaseEntity {

    /** 姓名*/
    @ApiModelProperty(value = "姓名", position = 11, required = true)
    @Column(length = 60)
    private String name;

    /** 关联公司的id */
    @ApiModelProperty(value = "关联公司的id", position = 13)
    @Column(length = 36)
    private String companyId;

    /** 身份证号 */
    @ApiModelProperty(value = "身份证号", position = 15)
    @Column(length = 36)
    private String identity;

    /** 办公电话 */
    @ApiModelProperty(value = "办公电话", position = 17)
    @Column(length = 36)
    private String officeNumber;

    /** 手机号 */
    @ApiModelProperty(value = "手机号", position = 19)
    @Column(length = 36)
    private String mobilePhone;

    /** 常住地址 */
    @ApiModelProperty(value = "常住地址", position = 21)
    @Column(length = 200)
    private String address;

    /** 电子邮箱 */
    @ApiModelProperty(value = "电子邮箱", position = 23)
    @Column(length = 100)
    private String email;

    /** 传真 */
    @ApiModelProperty(value = "传真", position = 25)
    @Column(length = 36)
    private String fax;

    /** 邮编 */
    @ApiModelProperty(value = "邮编", position = 27)
    @Column(length = 36)
    private String post;

    /** 职务 */
    @ApiModelProperty(value = "职务", position = 29)
    @Column(length = 36)
    private String position;

    /** 邮寄地址 */
    @ApiModelProperty(value = "邮寄地址", position = 31)
    @Column(length = 200)
    private String postAddress;

    /** 姓股东类型（股东、董事）名 */
    @ApiModelProperty(value = "股东类型（股东、董事）", position = 33)
    @Column(length = 200)
    private String shareholderType;

    /** 出资情况 */
    @ApiModelProperty(value = "出资情况", position = 35)
    @Column(length = 100)
    private String capitalContribution;

    /** 出资方式 */
    @ApiModelProperty(value = "出资方式", position = 37)
    @Column(length = 60)
    private String contributionMethod;

    /** 股权比例 */
    @ApiModelProperty(value = "股权比例", position = 39)
    private Double shareRatio;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 41)
    @Column(length = 500)
    private String note;

    /** 负责人类型 */
    @ApiModelProperty(value = "负责人类型", position = 43)
    @Column(length = 36)
    private String type;

    /** 证件类型 */
    @ApiModelProperty(value = "证件类型", position = 45)
    @Column(length = 36)
    private String credentialType;

    /** 币别 */
    @ApiModelProperty(value = "证件类型", position = 45)
    @Column(length = 36)
    private String currency;

    /** 与负责人关系 */
    @ApiModelProperty(value = "证件类型", position = 47)
    @Column(length = 200)
    private String relationship;

}