package com.cloudkeeper.leasing.company.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 供应商 VO
 * @author asher
 */
@ApiModel(value = "供应商 VO", description = "供应商 VO")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierAllVO extends SupplierVO {

    /** 客户经理*/
    @ApiModelProperty(value = "客户经理",position = 24)
    private String customerManager;

    /** 法人*/
    @ApiModelProperty(value = "法人", position = 25)
    @Transient
    private CompanyPersonnelVO corporation;

    /** 联系人DTOs*/
    @ApiModelProperty(value = "联系人DTOs", position = 26)
    private List<CompanyPersonnelVO> contactList = new ArrayList<>();

    /** 联系人需要被删除的ID集合*/
    @ApiModelProperty(value = "联系人需要被删除的ID集合", position = 27)
    private List<String> contactDeleteList = new ArrayList<>();
}
