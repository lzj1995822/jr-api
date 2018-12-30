package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.identity.repository.CountryRepository;
import com.cloudkeeper.leasing.identity.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 村 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {

    /** 村 repository */
    private final CountryRepository countryRepository;

    @Override
    protected BaseRepository<Country> getBaseRepository() {
        return countryRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("des", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("townid", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<Country> findAllByTownId(String townId) {
        return countryRepository.findAllByTownid(townId);
    }
}