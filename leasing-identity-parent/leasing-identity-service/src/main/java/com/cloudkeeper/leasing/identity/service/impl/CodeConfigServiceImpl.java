package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.identity.repository.CodeConfigRepository;
import com.cloudkeeper.leasing.identity.service.CodeConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * 编码配置 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CodeConfigServiceImpl extends BaseServiceImpl<CodeConfig> implements CodeConfigService {

    /** 编码配置 repository */
    private final CodeConfigRepository codeConfigRepository;

    @Override
    protected BaseRepository<CodeConfig> getBaseRepository() {
        return codeConfigRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("businessType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("branchCompany", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("leasingType", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("separator", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("serialNumberCycle", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}