package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import com.cloudkeeper.leasing.identity.domain.JrResource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 功能室 VO
 * @author wj
 */
@ApiModel(value = "功能室 VO", description = "功能室 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgRoomVO extends BaseVO {

    /** 功能室类型 */
    @ApiModelProperty(value = "功能室类型", position = 10)
    private String type;

    /** 镇id */
    @ApiModelProperty(value = "镇id", position = 12)
    private String townId;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 14)
    private String name;

    /** 统一编码 */
    @ApiModelProperty(value = "统一编码", position = 16)
    private String codeNumber;

    /** 经度 */
    @ApiModelProperty(value = "经度", position = 18)
    private String locateX;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", position = 20)
    private String locateY;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 22)
    private String des;
    
    /** 功能室图片集合 */
    @ApiModelProperty(value = "功能室图片集合", position = 24)
    private List<JrResource> jrResourceList;

    /** 镇名 */
    @ApiModelProperty(value = "镇名", position = 22)
    private String townName;

}