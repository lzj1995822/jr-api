package com.cloudkeeper.leasing.identity.dto.city;

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
 * 市 查询DTO
 * @author wj
 */
@ApiModel(value = "市 查询DTO", description = "市 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CitySearchable extends BaseSearchable {

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12)
    private String name;

}