package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Point;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 实践点 repository
 * @author wj
 */
@Repository
public interface PointRepository extends BaseRepository<Point> {

}