package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.base.service.BaseService;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 村 service
 * @author wj
 */
public interface CountryService extends BaseService<Country> {

    List<Country> findAllByTownId(String townId);
}