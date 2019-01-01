package com.cloudkeeper.leasing.identity.dto.townactivity;

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
 * 镇活动 DTO
 * @author hf
 */
@ApiModel(value = "镇活动 DTO", description = "镇活动 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownActivityDTO extends BaseEditDTO {

    /** 镇id */
    @ApiModelProperty(value = "镇id", position = 10)
    private String townId;

    /** 活动id */
    @ApiModelProperty(value = "活动id", position = 12)
    private String activityId;

}