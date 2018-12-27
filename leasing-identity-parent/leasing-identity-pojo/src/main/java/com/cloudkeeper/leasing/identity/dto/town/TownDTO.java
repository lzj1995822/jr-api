package com.cloudkeeper.leasing.identity.dto.town;

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
 * 所站 DTO
 * @author wj
 */
@ApiModel(value = "所站 DTO", description = "所站 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownDTO extends BaseEditDTO {

    /** 编码 */
    @ApiModelProperty(value = "编码", position = 11, required = true)
    private String coding;

    /** 所站名称 */
    @ApiModelProperty(value = "所站名称", position = 13, required = true)
    private String townName;

    /** 经度 */
    @ApiModelProperty(value = "经度", position = 15)
    private String longitude;

    /** 纬度 */
    @ApiModelProperty(value = "纬度", position = 17)
    private String latitude;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 19)
    private String remark;

}