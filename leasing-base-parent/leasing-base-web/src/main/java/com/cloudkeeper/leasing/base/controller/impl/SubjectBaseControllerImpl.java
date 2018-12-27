package com.cloudkeeper.leasing.base.controller.impl;

import com.cloudkeeper.leasing.base.controller.SubjectBaseController;
import com.cloudkeeper.leasing.base.domain.SubjectBaseEntity;
import com.cloudkeeper.leasing.base.dto.BaseDTO;
import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.base.service.SubjectBaseService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * subject base controller
 * @param <E> entity
 * @param <D> dto
 * @param <S> searchable
 * @param <V> vo
 * @author lixin.shao
 */
public abstract class SubjectBaseControllerImpl<E extends SubjectBaseEntity, D extends BaseDTO, S extends BaseSearchable, V> extends BaseControllerImpl<E, D, S, V> implements SubjectBaseController<D, S, V> {

    /**
     * 获取案件base service
     * @return service
     */
    protected abstract SubjectBaseService<E> getSubjectBaseService();

    @Override
    protected BaseService<E> getBaseService() {
        return getSubjectBaseService();
    }

    @Override
    public Result<List<V>> findBySubjectId(@ApiParam(value = "案件id", required = true) @PathVariable String subjectId) {
        List<E> entityList = getSubjectBaseService().findBySubjectIdOrderByModifiedAtDesc(subjectId);
        return Result.of(E.convert(entityList, getVOClass()));
    }

    @Override
    public Result deleteBySubjectId(@ApiParam(value = "案件id", required = true) @PathVariable String subjectId){
        getSubjectBaseService().deleteBySubjectId(subjectId);
        return Result.ofDeleteSuccess();
    }

}
