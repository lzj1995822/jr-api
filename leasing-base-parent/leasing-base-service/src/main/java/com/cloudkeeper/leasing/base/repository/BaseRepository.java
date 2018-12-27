package com.cloudkeeper.leasing.base.repository;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础 数据库操作
 * @param <T> 泛型
 * @author jerry
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T>, QuerydslPredicateExecutor<T> {
}
