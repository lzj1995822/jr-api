package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.OrgPerson;
import com.cloudkeeper.leasing.identity.repository.OrgPersonRepository;
import com.cloudkeeper.leasing.identity.service.OrgPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 组织架构人员 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrgPersonServiceImpl extends BaseServiceImpl<OrgPerson> implements OrgPersonService {

    /** 组织架构人员 repository */
    private final OrgPersonRepository orgPersonRepository;

    @Override
    protected BaseRepository<OrgPerson> getBaseRepository() {
        return orgPersonRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("sex", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("positon", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("duty", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("remark", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("level", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}