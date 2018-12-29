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
 * 镇 DTO
 * @author wj
 */
@ApiModel(value = "镇 DTO", description = "镇 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownDTO extends BaseEditDTO {

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12)
    private String name;

}