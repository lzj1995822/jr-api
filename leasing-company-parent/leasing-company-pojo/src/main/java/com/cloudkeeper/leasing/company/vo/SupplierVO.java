package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 供应商 VO
 * @author asher
 */
@ApiModel(value = "供应商 VO", description = "供应商 VO")
@Getter
@Setter
public class SupplierVO extends CommonCompanyVO {

    /** 公司父表id */
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    private String parentId;

    /** 供应商类别 */
    @ApiModelProperty(value = "供应商类别", position = 13, required = true)
    private String category;

    /** 全部回购 */
    @ApiModelProperty(value = "全部回购", position = 15)
    private Integer repurchase;

    /** 是否可用合同章*/
    @ApiModelProperty(value = "是否可用合同章", position = 17)
    private Integer contractChapter;

    /** 是否密码管控*/
    @ApiModelProperty(value = "是否密码管控", position = 19)
    private Integer cipherControl;

    /** 买卖合同是否适合补充条款*/
    @ApiModelProperty(value = "买卖合同是否适合补充条款", position = 21)
    private Integer applyAttachment;

    /** 是否可以网银汇款*/
    @ApiModelProperty(value = "是否可以网银汇款", position = 23)
    private Integer cyberBank;

}