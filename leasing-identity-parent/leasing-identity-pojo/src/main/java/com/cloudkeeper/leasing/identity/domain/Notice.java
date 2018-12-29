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
 * 通知
 * @author wj
 */
@ApiModel(value = "通知", description = "通知")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jr_notice")
public class Notice extends BaseEntity {

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 10)
    private String title;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 12)
    private String content;

    /** 附件url */
    @ApiModelProperty(value = "附件url", position = 14)
    private String url;

}