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

@ApiModel(value = "countvo", description = "countvo")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CountVO implements Serializable {
    /** 数量 */
    @ApiModelProperty(value = "数量", position = 12, required = true)
    private Long count;
}
