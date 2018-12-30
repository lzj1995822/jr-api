package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Town;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 镇 repository
 * @author wj
 */
@Repository
public interface TownRepository extends BaseRepository<Town> {
   Optional<Town> findById(String id);

}