package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import com.cloudkeeper.leasing.identity.repository.OrgCenterRepository;
import com.cloudkeeper.leasing.identity.service.OrgCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 分中心 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgCenterServiceImpl extends BaseServiceImpl<OrgCenter> implements OrgCenterService {

    /** 分中心 repository */
    private final OrgCenterRepository orgCenterRepository;

    @Override
    protected BaseRepository<OrgCenter> getBaseRepository() {
        return orgCenterRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("codeNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("locateX", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("locateY", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}