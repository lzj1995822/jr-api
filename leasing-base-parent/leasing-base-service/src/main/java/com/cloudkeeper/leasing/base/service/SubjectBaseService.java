package com.cloudkeeper.leasing.base.service;

import com.cloudkeeper.leasing.base.domain.SubjectBaseEntity;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 基础案件service
 * @param <T> 实体泛型
 * @author lixin.shao
 */
public interface SubjectBaseService<T extends SubjectBaseEntity> extends BaseService<T> {

    /**
     * 案件id 获取数据
     * @param subjectId 案件id
     * @return 集合
     */
    List<T> findBySubjectId(@Nonnull String subjectId);

    /**
     * 案件 id 删除数据
     * @param subjectId 案件id
     */
    void deleteBySubjectId(@Nonnull String subjectId);

    /**
     * 案件id 获取数据（修改时间降序排列）
     * @param subjectId 案件id
     * @return 集合
     */
    List<T> findBySubjectIdOrderByModifiedAtDesc(@Nonnull String subjectId);

}
