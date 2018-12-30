package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

/**
 * 村 repository
 * @author wj
 */
@Repository
public interface CountryRepository extends BaseRepository<Country> {
   Optional<Country> findById(String id);

    List<Country> findAllBy();
}