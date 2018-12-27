package com.cloudkeeper.leasing.base.controller.impl;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.dto.BaseDTO;
import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * base controller
 * @param <E> entity
 * @param <D> dto
 * @param <S> searchable
 * @param <V> vo
 * @author jerry
 */
public abstract class BaseControllerImpl<E extends BaseEntity, D extends BaseDTO, S extends BaseSearchable, V> implements BaseController<D, S, V> {

    /**
     * 获取 service
     * @return service
     */
    protected abstract BaseService<E> getBaseService();

    /**
     * 获取 vo 泛型类型
     * @return 泛型类型
     */
    protected Class<V> getVOClass() {
        return (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[3];
    }

    /**
     * 获取实体泛型类型
     * @return 泛型类型
     */
    protected Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Result<V> findOne(@ApiParam(value = "id", required = true) @PathVariable String id) {
        Optional<E> optional = getBaseService().findOptionalById(id);
        return optional.map(role -> Result.of(role.convert(getVOClass()))).orElseGet(Result::ofNotFound);
    }

    @Override
    public Result<V> add(@ApiParam(value = "dto", required = true) @RequestBody @Validated D dto) {
        E e = getBaseService().save(dto.convert(getEntityClass()));
        return Result.ofAddSuccess(e.convert(getVOClass()));
    }

    @Override
    public Result<V> update(@ApiParam(value = "id", required = true) @PathVariable String id,
                            @ApiParam(value = "dto", required = true) @RequestBody @Validated D dto) {
        Optional<E> optional = getBaseService().findOptionalById(id);
        if (!optional.isPresent()) {
            return Result.ofLost();
        }
        E e = optional.get();
        BeanUtils.copyProperties(dto, e);
        e = getBaseService().save(e);
        return Result.ofUpdateSuccess(e.convert(getVOClass()));
    }

    @Override
    public Result delete(@ApiParam(value = "id", required = true) @PathVariable String id) {
        getBaseService().deleteById(id);
        return Result.ofDeleteSuccess();
    }

    @Override
    public Result<List<V>> list(@ApiParam(value = "查询条件", required = true) @RequestBody S searchable,
                                @ApiParam(value = "排序", required = true) Sort sort) {
        List<E> entityList = getBaseService().findAll(searchable, sort);
        List<V> voList = E.convert(entityList, getVOClass());
        return Result.of(voList);
    }

    @Override
    public Result<Page<V>> page(@ApiParam(value = "查询条件", required = true) @RequestBody S searchable,
                                @ApiParam(value = "分页条件", required = true) Pageable pageable) {
        Page<E> entityPage = getBaseService().findAll(searchable, pageable);
        Page<V> voPage = E.convert(entityPage, getVOClass());
        return Result.of(voPage);
    }
}
