package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.CenterUser;
import com.cloudkeeper.leasing.identity.repository.CenterUserRepository;
import com.cloudkeeper.leasing.identity.service.CenterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 分中心人员 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CenterUserServiceImpl extends BaseServiceImpl<CenterUser> implements CenterUserService {

    /** 分中心人员 repository */
    private final CenterUserRepository centerUserRepository;

    @Override
    protected BaseRepository<CenterUser> getBaseRepository() {
        return centerUserRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("jobNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("gender", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("position", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}