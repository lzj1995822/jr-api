package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.FunctionRoomUser;
import com.cloudkeeper.leasing.identity.repository.FunctionRoomUserRepository;
import com.cloudkeeper.leasing.identity.service.FunctionRoomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 功能室人员 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FunctionRoomUserServiceImpl extends BaseServiceImpl<FunctionRoomUser> implements FunctionRoomUserService {

    /** 功能室人员 repository */
    private final FunctionRoomUserRepository functionRoomUserRepository;

    @Override
    protected BaseRepository<FunctionRoomUser> getBaseRepository() {
        return functionRoomUserRepository;
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