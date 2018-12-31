package com.cloudkeeper.leasing.identity.dto.notice;

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
 * 通知 DTO
 * @author wj
 */
@ApiModel(value = "通知 DTO", description = "通知 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO extends BaseEditDTO {

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 10)
    private String title;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 12)
    private String content;

    /** 附件url */
    @ApiModelProperty(value = "附件url", position = 14)
    private String url;

    /** 文化类型 */
    @ApiModelProperty(value = "文化类型", position = 16)
    private String type;

}