package com.cloudkeeper.leasing.company.dto.guarantor;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
import com.cloudkeeper.leasing.base.dto.BaseUpdateDTO;
import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanyDTO;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 担保人 DTO
 * @author asher
 */
@ApiModel(value = "担保人 DTO", description = "担保人 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorDTO extends BaseUpdateDTO {

    /** 担保人姓名 */
    @ApiModelProperty(value = "担保人姓名", position = 11, required = true)
    private String name;

    /** 证件类型 */
    @ApiModelProperty(value = "证件类型", position = 13)
    private String certificateType;

    /** 身份证号 */
    @ApiModelProperty(value = "身份证号", position = 15)
    private String identity;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期", position = 17)
    private LocalDate birthday;

    /** 性别（0女男1） */
    @ApiModelProperty(value = "性别（0女男1）", position = 19)
    private Integer sex;

    /** 单位地址 */
    @ApiModelProperty(value = "单位地址", position = 21)
    private String companyAddress;

    /** 电话 */
    @ApiModelProperty(value = "电话", position = 23)
    private String phone;

    /** 家庭地址 */
    @ApiModelProperty(value = "家庭地址", position = 25)
    private String homeAddress;

    /** 工作单位 */
    @ApiModelProperty(value = "工作单位", position = 27)
    private String companyName;

    /** 联系人姓名 */
    @ApiModelProperty(value = "联系人姓名", position = 29)
    private String contactName;

    /** 联系人电话 */
    @ApiModelProperty(value = "联系人电话", position = 31)
    private String contactPhone;

    /** 联系人地址 */
    @ApiModelProperty(value = "联系人地址", position = 33)
    private String contactAddress;

    /** 担保资产 DTO*/
    @ApiModelProperty(value = "担保资产",position =35)
    private List<SecuredAssetsDTO> assetsList = new ArrayList<>();

    /** 担保资产删除 ID集合*/
    @ApiModelProperty(value = "担保资产删除 ID集合",position =37)
    private List<String> SecuredAssetsDeleteList =new ArrayList<>();

}