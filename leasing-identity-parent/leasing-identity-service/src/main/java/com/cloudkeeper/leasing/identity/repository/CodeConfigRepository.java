package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 编码配置 repository
 * @author asher
 */
@Repository
public interface CodeConfigRepository extends BaseRepository<CodeConfig> {

}