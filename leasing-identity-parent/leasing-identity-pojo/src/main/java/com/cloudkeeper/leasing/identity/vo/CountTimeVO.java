package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "时间活动统计VO", description = "时间活动统计 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CountTimeVO implements Serializable {
    /** 时间 */
    @ApiModelProperty(value = "时间", position = 10, required = true)
    private String time;
    /** 数量 */
    @ApiModelProperty(value = "数量", position = 12, required = true)
    private Long count;
}
