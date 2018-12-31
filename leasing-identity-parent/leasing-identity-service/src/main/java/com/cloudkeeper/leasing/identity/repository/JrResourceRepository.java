package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件上传 repository
 * @author hf
 */
@Repository
public interface JrResourceRepository extends BaseRepository<JrResource> {
        List<JrResource> findByConnectId(String connectid);
}