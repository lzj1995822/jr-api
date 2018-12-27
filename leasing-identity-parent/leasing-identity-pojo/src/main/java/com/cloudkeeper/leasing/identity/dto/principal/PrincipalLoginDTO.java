package com.cloudkeeper.leasing.identity.dto.principal;

import com.cloudkeeper.leasing.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录dto
 * @author jerry
 */
@ApiModel(value = "用户登录dto", description = "用户登录dto")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalLoginDTO extends BaseDTO {

    /** 登录名*/
    @ApiModelProperty(value = "登录名", position = 10, required = true)
    @NotBlank
    private String code;

    /** 密码*/
    @ApiModelProperty(value = "密码", position = 14, required = true)
    @NotBlank
    private String password;
}
