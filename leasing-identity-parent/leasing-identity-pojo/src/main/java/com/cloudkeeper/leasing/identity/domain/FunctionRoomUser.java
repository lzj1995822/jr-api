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
 * 功能室人员
 * @author wj
 */
@ApiModel(value = "功能室人员", description = "功能室人员")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_function_room_user")
public class FunctionRoomUser extends BaseEntity {

    @ApiModelProperty(value = "功能室id", position = 11, required = true)
    private String functionRoomId;

    @ApiModelProperty(value = "工号", position = 13, required = true)
    private String jobNumber;

    @ApiModelProperty(value = "姓名", position = 15)
    private String name;

    @ApiModelProperty(value = "性别", position = 17, required = true)
    private String gender;

    @ApiModelProperty(value = "职位", position = 19)
    private String position;

    @ApiModelProperty(value = "备注", position = 21)
    private String remark;

}