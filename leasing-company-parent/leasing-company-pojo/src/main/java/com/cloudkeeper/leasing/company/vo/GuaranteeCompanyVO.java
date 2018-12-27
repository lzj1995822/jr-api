package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 担保公司 VO
 * @author asher
 */
@ApiModel(value = "担保公司 VO", description = "担保公司 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuaranteeCompanyVO extends BaseVO {

    /** 公司父表id */
    @ApiModelProperty(value = "公司父表id", position = 11, required = true)
    private String parentId;

    /** 职工人数 */
    @ApiModelProperty(value = "职工人数", position = 13)
    private Integer staffNumber;

    /** 登记地址*/
    @ApiModelProperty(value = "登记地址", position = 15)
    private String registeredAddress;

    /**当前版本下的历史id*/
    @ApiModelProperty(value = "当前版本下的历史id", position = 25)
    private String pkId;

}