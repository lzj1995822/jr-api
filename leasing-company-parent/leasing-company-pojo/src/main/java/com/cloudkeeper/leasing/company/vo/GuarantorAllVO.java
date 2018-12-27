package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 担保人、名下资产 VO
 * @author asher
 */
@ApiModel(value = "担保人、名下资产 VO", description = "担保人、名下资产 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GuarantorAllVO extends GuarantorVO{

    /** 客户经理*/
    @ApiModelProperty(value = "客户经理",position = 37)
    private String customerManager;

    /** 名下资产VO */
    @ApiModelProperty(value = "名下资产VO", position = 39)
    private List<SecuredAssetsVO> assetsList;


}
