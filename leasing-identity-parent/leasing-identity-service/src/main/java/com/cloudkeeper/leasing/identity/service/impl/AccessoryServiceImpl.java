package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.base.utils.FileUtil;
import com.cloudkeeper.leasing.identity.constant.PropertyConfigurationConstants;
import com.cloudkeeper.leasing.identity.domain.Accessory;
import com.cloudkeeper.leasing.identity.domain.PropertyConfiguration;
import com.cloudkeeper.leasing.identity.dto.accessory.AccessorySearchable;
import com.cloudkeeper.leasing.identity.dto.propertyconfiguration.PropertyConfigurationSearchable;
import com.cloudkeeper.leasing.identity.repository.AccessoryRepository;
import com.cloudkeeper.leasing.identity.service.AccessoryService;
import com.cloudkeeper.leasing.identity.service.PropertyConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 系统附件 service
 * @author asher
 */
@Service
public class AccessoryServiceImpl extends BaseServiceImpl<Accessory> implements AccessoryService {

    /** 系统附件 repository */
    @Autowired
    private AccessoryRepository accessoryRepository;

    @Override
    protected BaseRepository<Accessory> getBaseRepository() {
        return accessoryRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("masterObject", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("path", ExampleMatcher.GenericPropertyMatchers.contains());
    }

}