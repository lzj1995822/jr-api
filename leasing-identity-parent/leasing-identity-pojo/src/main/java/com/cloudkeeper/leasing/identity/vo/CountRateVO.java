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

@ApiModel(value = "活动类型百分比统计VO", description = "活动类型百分比统计VO VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CountRateVO implements Serializable {
    /** 活动类型 */
    @ApiModelProperty(value = "活动类型", position = 10, required = true)
    private String activitytype;
    /** 百分比 */
    @ApiModelProperty(value = "百分比", position = 12, required = true)
    private Double rate;

    @Override
    public String toString() {
        return "CountRateVO{" +
                "activitytype='" + activitytype + '\'' +
                ", rate=" + rate +
                '}';
    }
}
