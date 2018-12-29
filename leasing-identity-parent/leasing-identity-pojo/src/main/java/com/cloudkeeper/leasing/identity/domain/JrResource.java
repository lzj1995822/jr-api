package com.cloudkeeper.leasing.identity.domain;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件上传
 * @author hf
 */
@ApiModel(value = "文件上传", description = "文件上传")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_resource")
public class JrResource extends BaseEntity {

    /** id*/
    @ApiModelProperty(value = "链接id", position = 2, required = true)
    @Column(length = 36)
    private String connectId;

    /** 地址*/
    @ApiModelProperty(value = "地址", position = 3, required = true)
    @Column(length = 36)
    private String url;
    /** 表名*/
    @ApiModelProperty(value = "表名", position = 3, required = true)
    @Column(length = 36)
    private String type;
}