package com.cloudkeeper.leasing.identity.dto.orgcenter;

import com.cloudkeeper.leasing.base.dto.BaseEditDTO;
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

/**
 * 分中心 DTO
 * @author wj
 */
@ApiModel(value = "分中心组织 DTO", description = "分中心组织 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgCenterDTO extends BaseEditDTO {

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

    /** 名称 */
    @ApiModelProperty(value = "路径", position = 22)
    private String path;

}