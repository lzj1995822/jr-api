package com.cloudkeeper.leasing.identity.dto.point;

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
 * 实践点 查询DTO
 * @author wj
 */
@ApiModel(value = "实践点 查询DTO", description = "实践点 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PointSearchable extends BaseSearchable {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 实践点类型 */
    @ApiModelProperty(value = "实践点类型", position = 10)
    private String type;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 10)
    private String orgId;

    /** 组织类型 */
    @ApiModelProperty(value = "组织类型", position = 10)
    private String orgType;

}