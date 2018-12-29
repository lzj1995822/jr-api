package com.cloudkeeper.leasing.identity.dto.orgperson;

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
 * 组织架构人员 DTO
 * @author wj
 */
@ApiModel(value = "组织架构人员 DTO", description = "组织架构人员 DTO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrgPersonDTO extends BaseEditDTO {

    /** 名称 */
    @ApiModelProperty(value = "名称", position = 10)
    private String name;

    /** 性别 */
    @ApiModelProperty(value = "性别", position = 12)
    private String sex;

    /** 职位 */
    @ApiModelProperty(value = "职位", position = 14)
    private String positon;

    /** 行政职务 */
    @ApiModelProperty(value = "行政职务", position = 16)
    private String duty;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 18)
    private String remark;

    /** 层级 */
    @ApiModelProperty(value = "层级", position = 20)
    private String level;

    /** 组织类型 */
    @ApiModelProperty(value = "组织类型", position = 22)
    private String type;

    /** 组织id */
    @ApiModelProperty(value = "组织id", position = 24)
    private String orgId;

}