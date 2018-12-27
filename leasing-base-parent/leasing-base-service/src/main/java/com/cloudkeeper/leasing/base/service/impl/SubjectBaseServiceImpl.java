package com.cloudkeeper.leasing.base.service.impl;

import com.cloudkeeper.leasing.base.domain.SubjectBaseEntity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.repository.SubjectBaseRepository;
import com.cloudkeeper.leasing.base.service.SubjectBaseService;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 基础案件service 实现
 * @param <T> 泛型
 * @author lixin.shao
 */
public abstract class SubjectBaseServiceImpl<T extends SubjectBaseEntity> extends BaseServiceImpl<T> implements SubjectBaseService<T> {

    /**
     * 子类实现该方法
     * @return IBaseRepository
     * @author lixin.shao
     */
    protected abstract SubjectBaseRepository<T> getSubjectBaseRepository();

    @Override
    protected BaseRepository<T> getBaseRepository() {
        return getSubjectBaseRepository();
    }

    @Override
    public List<T> findBySubjectId(@Nonnull String subjectId) {
        return getSubjectBaseRepository().findBySubjectId(subjectId);
    }

    @Override
    public List<T> findBySubjectIdOrderByModifiedAtDesc(@Nonnull String subjectId) {
        return getSubjectBaseRepository().findBySubjectIdOrderByModifiedAtDesc(subjectId);
    }

    @Override
    public void deleteBySubjectId(@Nonnull String subjectId){
        getSubjectBaseRepository().deleteBySubjectId(subjectId);
    }

}
