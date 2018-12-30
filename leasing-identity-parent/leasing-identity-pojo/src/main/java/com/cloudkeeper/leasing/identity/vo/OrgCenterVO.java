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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分中心 VO
 * @author wj
 */
@ApiModel(value = "分中心组织 VO", description = "分中心组织 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgCenterVO extends BaseVO {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 统一编码 */
    @ApiModelProperty(value = "统一编码", position = 12)
    private String codeNumber;

    /** 类型 */
    @ApiModelProperty(value = "类型", position = 14)
    private String type;

    /** 城市id */
    @ApiModelProperty(value = "城市id", position = 16)
    private String cityId;

    /** 经度 */
    @ApiModelProperty(value = "经度", position = 18)
    private String locateX;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", position = 20)
    private String locateY;
    
    /** 功能分中心图片集合 */
    @ApiModelProperty(value = "功能分中心图片集合", position = 22)
    private List<JrResource> jrResourceList;

}