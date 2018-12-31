package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.dto.activity.ActivityDTO;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import com.cloudkeeper.leasing.identity.vo.ActivityVO;
import com.cloudkeeper.leasing.identity.vo.CountActivity1Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 活动 controller
 * @author wj
 */
@Api(value = "活动", tags = "活动")
@RequestMapping("/activity")
public interface ActivityController extends BaseController<ActivityDTO, ActivitySearchable, ActivityVO> {
    /** 具体活动执行情况表*/
    @ApiModelProperty(value = "具体活动执行情况表", position = 10, required = true)
    @GetMapping("/countRecordlist")
    RestPageImpl<CountActivity1Vo> countRecordlist(Pageable pageable) ;
    
    
}