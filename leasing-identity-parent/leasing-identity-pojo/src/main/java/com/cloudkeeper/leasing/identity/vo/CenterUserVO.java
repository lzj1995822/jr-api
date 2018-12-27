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
 * 分中心人员 VO
 * @author wj
 */
@ApiModel(value = "分中心人员 VO", description = "分中心人员 VO")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CenterUserVO extends BaseVO {

    /** 分中心id */
    @ApiModelProperty(value = "分中心id", position = 11, required = true)
    private String centerId;

    /** 工号 */
    @ApiModelProperty(value = "工号", position = 13, required = true)
    private String jobNumber;

    /** 姓名 */
    @ApiModelProperty(value = "姓名", position = 15)
    private String name;

    /** 性别 */
    @ApiModelProperty(value = "性别", position = 17)
    private String gender;

    /** 职位 */
    @ApiModelProperty(value = "职位", position = 19)
    private String position;

    /** 备注 */
    @ApiModelProperty(value = "备注", position = 21)
    private String remark;

}