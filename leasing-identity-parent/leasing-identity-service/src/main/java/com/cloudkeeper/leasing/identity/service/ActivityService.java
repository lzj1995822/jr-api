package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import com.cloudkeeper.leasing.identity.vo.CountActivity1Vo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 活动 service
 * @author wj
 */
public interface ActivityService extends BaseService<Activity> {
    
    /**
     * 根据类型和权限分页
     * @return
     */
    Page<Activity> pageByTypeAndPermission(Pageable pageable, ActivitySearchable activitySearchable);

    @Nonnull
    @Override
    Activity save(@Nonnull Activity entity);
    RestPageImpl<CountActivity1Vo> countRecordlist(Pageable pageable) ;
}