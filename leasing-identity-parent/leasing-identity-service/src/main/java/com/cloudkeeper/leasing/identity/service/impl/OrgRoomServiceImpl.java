package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import com.cloudkeeper.leasing.identity.repository.OrgRoomRepository;
import com.cloudkeeper.leasing.identity.service.OrgRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 功能室 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgRoomServiceImpl extends BaseServiceImpl<OrgRoom> implements OrgRoomService {

    /** 功能室 repository */
    private final OrgRoomRepository orgRoomRepository;

    @Override
    protected BaseRepository<OrgRoom> getBaseRepository() {
        return orgRoomRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("codeNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("locateX", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("locateY", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}