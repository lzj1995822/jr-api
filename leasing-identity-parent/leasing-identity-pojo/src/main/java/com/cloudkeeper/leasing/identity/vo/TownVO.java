package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import com.cloudkeeper.leasing.identity.domain.JrResource;
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
import java.util.List;

/**
 * 镇 VO
 * @author wj
 */
@ApiModel(value = "镇 VO", description = "镇 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TownVO extends BaseVO {

    /** 描述 */
    @ApiModelProperty(value = "描述", position = 10)
    private String des;

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 12)
    private String name;

    /** 市id */
    @ApiModelProperty(value = "市id", position = 14)
    private String cityid;

    /** 图片 */
    @ApiModelProperty(value = "图片", position = 16)
    private String path;

    /** 镇图片集合 */
    @ApiModelProperty(value = "镇图片集合", position = 18)
    private List<JrResource> jrResourceList;

}