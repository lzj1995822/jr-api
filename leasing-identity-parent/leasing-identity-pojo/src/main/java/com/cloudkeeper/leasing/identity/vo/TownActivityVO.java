package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
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
 * 镇活动 VO
 * @author hf
 */
@ApiModel(value = "镇活动 VO", description = "镇活动 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownActivityVO extends BaseVO {

    /** 镇id */
    @ApiModelProperty(value = "镇id", position = 10)
    private String townId;

    /** 活动id */
    @ApiModelProperty(value = "活动id", position = 12)
    private String activityId;

}