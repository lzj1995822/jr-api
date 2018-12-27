package com.cloudkeeper.leasing.identity.dto.townfunctionroom;

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
 * 所站功能室建设 DTO
 * @author wj
 */
@ApiModel(value = "所站功能室建设 DTO", description = "所站功能室建设 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownFunctionRoomDTO extends BaseEditDTO {

    /** 所站id */
    @ApiModelProperty(value = "所站id", position = 11, required = true)
    private String townId;

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 13, required = true)
    private String coding;

    /** 功能室内名称 */
    @ApiModelProperty(value = "功能室内名称", position = 15)
    private String name;

    /** 文化类别 */
    @ApiModelProperty(value = "文化类别", position = 17)
    private String culturalCategory;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 19)
    private String remark;

}