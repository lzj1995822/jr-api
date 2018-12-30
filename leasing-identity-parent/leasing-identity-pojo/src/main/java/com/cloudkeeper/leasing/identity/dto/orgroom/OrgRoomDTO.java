package com.cloudkeeper.leasing.identity.dto.orgroom;

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
 * 功能室 DTO
 * @author wj
 */
@ApiModel(value = "功能室 DTO", description = "功能室 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgRoomDTO extends BaseEditDTO {

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
}