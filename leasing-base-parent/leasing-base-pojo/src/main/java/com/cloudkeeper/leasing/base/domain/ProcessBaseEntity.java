package com.cloudkeeper.leasing.base.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 基础实体类
 * @author lixin.shao
 */
@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public abstract class ProcessBaseEntity extends BaseEntity {

    /** 流程状态*/
    @ApiModelProperty(value = "流程状态", position = 8)
    @Column(length = 30)
    private String processStatus;

    /** 流程id */
    @ApiModelProperty(value = "流程id ", position = 8)
    @Column(length = 50)
    private String processStatusId;

}
