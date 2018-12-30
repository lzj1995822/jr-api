package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 村 repository
 * @author wj
 */
@Repository
public interface CountryRepository extends BaseRepository<Country> {

    List<Country> findAllBy();
}