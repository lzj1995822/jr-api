package com.cloudkeeper.leasing.identity.dto.country;

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
 * 村 查询DTO
 * @author wj
 */
@ApiModel(value = "村 查询DTO", description = "村 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CountrySearchable extends BaseSearchable {

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12)
    private String name;

    /** 积分 */
    @ApiModelProperty(value = "积分", position = 14)
    private Double score;
    /** 镇id */
    @ApiModelProperty(value = "镇id", position = 16)
    private String townid;
}