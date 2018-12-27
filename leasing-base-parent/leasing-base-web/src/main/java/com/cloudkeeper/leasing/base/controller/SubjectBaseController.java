package com.cloudkeeper.leasing.base.controller;

import com.cloudkeeper.leasing.base.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * subject base controller
 * @param <D> dto
 * @param <S> searchable
 * @param <V> vo
 * @author lixin.shao
 */
public interface SubjectBaseController<D, S, V> extends BaseController<D, S, V> {

    @ApiOperation(value = "案件 id 查询", notes = "案件 id 查询", position = 1)
    @GetMapping("/{subjectId}subjectId")
    Result<List<V>> findBySubjectId(@ApiParam(value = "案件id", required = true) @PathVariable String subjectId);

    @ApiOperation(value = "案件 id 删除数据", notes = "案件 id 删除数据", position = 1)
    @DeleteMapping("/{subjectId}subjectId")
    Result deleteBySubjectId(@ApiParam(value = "案件id", required = true) @PathVariable String subjectId);

}
