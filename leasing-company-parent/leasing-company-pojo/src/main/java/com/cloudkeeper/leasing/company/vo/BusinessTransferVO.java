package com.cloudkeeper.leasing.company.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务员转移、转移客户主记录 VO
 * @author asher
 */
@ApiModel(value = "业务员转移、转移客户主记录 VO", description = "业务员转移、转移客户主记录 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTransferVO extends BaseVO {

    /** 原始客户经理id */
    @ApiModelProperty(value = "原始客户经理id", position = 21, required = true)
    private String originId;

    /** 目的客户经理id */
    @ApiModelProperty(value = "目的客户经理id", position = 23, required = true)
    private String newId;

    /** 移交原因 */
    @ApiModelProperty(value = "原因类型", position = 15)
    private String reasonType;

    /** 移交原因 */
    @ApiModelProperty(value = "移交原因", position = 25)
    private String reason;

    /** 被转移客户集合 */
    @ApiModelProperty(value = "被转移客户集合", position = 27)
    private List<CustomerTransferVO> customerTransferVOList = new ArrayList<>();

    /** 原始客户经理*/
    @ApiModelProperty(value = "原始客户经理", position = 29)
    private String originName;

    /** 目的客户经理*/
    @ApiModelProperty(value = "目的客户经理", position = 31)
    private String newName;

}