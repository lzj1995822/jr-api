package com.cloudkeeper.leasing.company.dto.guaranteecompany;

import com.cloudkeeper.leasing.company.dto.commoncompany.CommonCompanySearchable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 担保公司 查询DTO
 * @author asher
 */
@ApiModel(value = "担保公司 查询DTO", description = "担保公司 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanySearchable extends CommonCompanySearchable {

    /** 公司父表id */
    @ApiModelProperty(value = "公司父表id", position = 11)
    private String parentId;

    /** 职工人数 */
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 公司担保人名称 */
    @ApiModelProperty(value = "公司担保人名称", position = 15)
    private String name;

    /** 统一信用代码 */
    @ApiModelProperty(value = "统一信用代码", position = 17)
    private String uniformCreditCode;

}