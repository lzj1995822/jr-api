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
public abstract class SubjectBaseEntity extends BaseEntity {

    /** 案件id*/
    @ApiModelProperty(value = "案件id", position = 9)
    @Column(name = "subjectId", length = 36)
    private String subjectId;

}
