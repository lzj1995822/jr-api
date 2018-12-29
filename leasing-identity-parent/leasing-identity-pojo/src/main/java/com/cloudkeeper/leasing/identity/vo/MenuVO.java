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
 * 菜单 VO
 * @author wj
 */
@ApiModel(value = "菜单 VO", description = "菜单 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO extends BaseVO {

    /** 菜单名称 */
    @ApiModelProperty(value = "菜单名称", position = 10)
    private String name;

    /** 菜单地址 */
    @ApiModelProperty(value = "菜单地址", position = 12)
    private String url;

    /** 父id */
    @ApiModelProperty(value = "父id", position = 10)
    private String parentId;

}