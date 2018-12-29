package com.cloudkeeper.leasing.identity.dto.jrresource;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
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
 * 文件上传 查询DTO
 * @author hf
 */
@ApiModel(value = "文件上传 查询DTO", description = "文件上传 查询DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JrResourceSearchable extends BaseSearchable {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10, required = true)
    private String name;

    /** 链接id */
    @ApiModelProperty(value = "链接id", position = 2, required = true)
    private String connectid;

    /** 地址 */
    @ApiModelProperty(value = "地址", position = 3, required = true)
    private String url;

    /** 表名 */
    @ApiModelProperty(value = "表名", position = 3, required = true)
    private String type;

}