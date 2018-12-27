package com.cloudkeeper.leasing.identity.dto.center;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
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
 * 分中心 查询DTO
 * @author wj
 */
@ApiModel(value = "分中心 查询DTO", description = "分中心 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CenterSearchable extends BaseSearchable {

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 11, required = true)
    private String coding;

    /** 分中心名称 */
    @ApiModelProperty(value = "分中心名称", position = 13, required = true)
    private String name;

    /** 文化类别 */
    @ApiModelProperty(value = "文化类别", position = 15)
    private String culturalType;

    /** 经度 */
    @ApiModelProperty(value = "经度", position = 17)
    private String longitude;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", position = 19)
    private String latitude;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 21)
    private String remark;

}