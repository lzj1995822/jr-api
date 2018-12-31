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
 * 通知 VO
 * @author wj
 */
@ApiModel(value = "通知 VO", description = "通知 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class NoticeVO extends BaseVO {

    /** 标题 */
    @ApiModelProperty(value = "标题", position = 10)
    private String title;

    /** 内容 */
    @ApiModelProperty(value = "内容", position = 12)
    private String content;

    /** 附件url */
    @ApiModelProperty(value = "附件url", position = 14)
    private String url;

    /** 发布单位 */
    @ApiModelProperty(value = "发布单位", position = 14)
    private String creator;


}