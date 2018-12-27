package com.cloudkeeper.leasing.identity.vo;

import com.cloudkeeper.leasing.base.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户vo
 * @author jerry
 */
@ApiModel(value = "用户vo", description = "用户vo")
@Getter
@Setter
public class PrincipalVO extends BaseVO {

    /** 登录名*/
    @ApiModelProperty(value = "登录名", position = 10)
    private String code;

    /** 姓名*/
    @ApiModelProperty(value = "姓名", position = 12)
    private String name;

    /** 密码*/
    @ApiModelProperty(value = "密码", position = 14)
    private String password;

    /** 手机*/
    @ApiModelProperty(value = "手机", position = 16)
    private String mobile;

    /** 邮箱*/
    @ApiModelProperty(value = "邮箱", position = 18)
    private String email;

    /** 描述*/
    @ApiModelProperty(value = "描述", position = 20)
    private String note;

    /** 主岗*/
    @ApiModelProperty(value = "主岗", position = 22)
    private OrganizationVO organizationVO;

    /** 兼职岗位*/
    @ApiModelProperty(value = "兼职岗位", position = 24)
    private List<OrganizationVO> organizationVOList;

    /** 菜单列表*/
    @ApiModelProperty(value = "菜单列表", position = 26)
    private List<String> menuCodeList;
}
