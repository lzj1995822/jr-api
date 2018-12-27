package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.TownFunctionRoom;
import com.cloudkeeper.leasing.identity.repository.TownFunctionRoomRepository;
import com.cloudkeeper.leasing.identity.service.TownFunctionRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 所站功能室建设 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TownFunctionRoomServiceImpl extends BaseServiceImpl<TownFunctionRoom> implements TownFunctionRoomService {

    /** 所站功能室建设 repository */
    private final TownFunctionRoomRepository townFunctionRoomRepository;

    @Override
    protected BaseRepository<TownFunctionRoom> getBaseRepository() {
        return townFunctionRoomRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("coding", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("culturalCategory", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}