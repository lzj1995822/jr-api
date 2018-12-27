package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.IdentificationNumber;
import com.cloudkeeper.leasing.company.repository.IdentificationNumberRepository;
import com.cloudkeeper.leasing.company.service.IdentificationNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

/**
 * 机器编号 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IdentificationNumberServiceImpl extends BaseServiceImpl<IdentificationNumber> implements IdentificationNumberService {

    /** 机器编号 repository */
    private final IdentificationNumberRepository identificationNumberRepository;

    @Override
    protected BaseRepository<IdentificationNumber> getBaseRepository() {
        return identificationNumberRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    public IdentificationNumber save(@Nonnull IdentificationNumber entity) {
        entity = super.save(entity);
        insertHis(entity.getId());
        return entity;
    }
}