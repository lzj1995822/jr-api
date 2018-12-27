package com.cloudkeeper.leasing.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础dto
 * @author jerry
 */
@Getter
@Setter
public abstract class BaseUpdateDTO extends BaseDTO {

    /** id*/
    @ApiModelProperty(value = "id", position = 5)
    private String id;

    /** 版本（乐观锁）*/
    @ApiModelProperty(value = "版本（乐观锁）", position = 6)
    private Integer version = 0;
}
