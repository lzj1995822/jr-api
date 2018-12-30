package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Activity;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.dto.activity.ActivitySearchable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;

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
}