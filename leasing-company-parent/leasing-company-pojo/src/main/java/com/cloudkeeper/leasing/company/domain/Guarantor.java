package com.cloudkeeper.leasing.company.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.company.vo.GuarantorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 担保人
 * @author asher
 */
@ApiModel(value = "担保人", description = "担保人")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "l_cp_guarantor")
public class Guarantor extends BaseEntity {

    /** 担保人姓名*/
    @ApiModelProperty(value = "担保人姓名", position = 11, required = true)
    @Column(length = 36)
    private String name;

    /** 证件类型*/
    @ApiModelProperty(value = "证件类型", position = 13)
    @Column(length = 36)
    private String certificateType;

    /** 身份证号*/
    @ApiModelProperty(value = "身份证号", position = 15)
    @Column(length = 36)
    private String identity;

    /** 出生日期*/
    @ApiModelProperty(value = "出生日期", position = 17)
    private LocalDate birthday;

    /** 性别（0女男1）*/
    @ApiModelProperty(value = "性别（0女男1）", position = 19)
    private Integer sex;

    /** 单位地址*/
    @ApiModelProperty(value = "单位地址", position = 21)
    @Column(length = 100)
    private String companyAddress;

    /** 电话*/
    @ApiModelProperty(value = "电话", position = 23)
    @Column(length = 36)
    private String phone;

    /** 家庭地址*/
    @ApiModelProperty(value = "家庭地址", position = 25)
    @Column(length = 100)
    private String homeAddress;

    /** 工作单位*/
    @ApiModelProperty(value = "工作单位", position = 27)
    @Column(length = 36)
    private String companyName;

    /** 联系人姓名*/
    @ApiModelProperty(value = "联系人姓名", position = 29)
    @Column(length = 36)
    private String contactName;

    /** 联系人电话*/
    @ApiModelProperty(value = "联系人电话", position = 31)
    @Column(length = 36)
    private String contactPhone;

    /** 联系人地址*/
    @ApiModelProperty(value = "联系人地址", position = 33)
    @Column(length = 100)
    private String contactAddress;

    /**
     * 当前版本下的历史id
     */
    @ApiModelProperty(value = "当前版本下的历史id", position = 35)
    @Transient
    private String pkId;

}