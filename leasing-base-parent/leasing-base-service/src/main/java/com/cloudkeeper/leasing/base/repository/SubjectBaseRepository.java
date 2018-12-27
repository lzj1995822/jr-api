package com.cloudkeeper.leasing.base.repository;

import com.cloudkeeper.leasing.base.domain.SubjectBaseEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 基础 案件模块数据库操作
 * @param <T> 泛型
 * @author lixin.shao
 */
@NoRepositoryBean
public interface SubjectBaseRepository<T extends SubjectBaseEntity> extends BaseRepository<T> {

    /**
     * 案件id 获取数据
     * @param subjectId 案件id
     * @return 集合
     */
    List<T> findBySubjectId(@Nonnull String subjectId);

    /**
     * 案件 id 删除数据
     * @param subjectId 案件id
     * @return 删除数量
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    int deleteBySubjectId(@Nonnull String subjectId);

    /**
     * 案件id 获取数据（修改时间降序排列）
     * @param subjectId 案件id
     * @return 集合
     */
    List<T> findBySubjectIdOrderByModifiedAtDesc(@Nonnull String subjectId);
}
