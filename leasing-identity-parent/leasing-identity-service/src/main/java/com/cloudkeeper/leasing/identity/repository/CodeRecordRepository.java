package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 编码生成记录 repository
 * @author asher
 */
@Repository
public interface CodeRecordRepository extends BaseRepository<CodeRecord> {

}