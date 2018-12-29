package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Notice;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 通知 repository
 * @author wj
 */
@Repository
public interface NoticeRepository extends BaseRepository<Notice> {

}