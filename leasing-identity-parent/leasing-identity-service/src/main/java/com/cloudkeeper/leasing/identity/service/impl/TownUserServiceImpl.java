package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.TownUser;
import com.cloudkeeper.leasing.identity.repository.TownUserRepository;
import com.cloudkeeper.leasing.identity.service.TownUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 所站人员 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownUserServiceImpl extends BaseServiceImpl<TownUser> implements TownUserService {

    /** 所站人员 repository */
    private final TownUserRepository townUserRepository;

    @Override
    protected BaseRepository<TownUser> getBaseRepository() {
        return townUserRepository;
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