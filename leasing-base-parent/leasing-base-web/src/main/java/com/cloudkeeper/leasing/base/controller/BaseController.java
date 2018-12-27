package com.cloudkeeper.leasing.base.controller;

import com.cloudkeeper.leasing.base.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * base controller
 * @param <D> dto
 * @param <S> searchable
 * @param <V> vo
 * @author jerry
 */
public interface BaseController<D, S, V> {

    /**
     * id 查询
     * @param id id
     * @return vo
     */
    @ApiOperation(value = "id 查询", notes = "id 查询")
    @GetMapping("/{id}")
    Result<V> findOne(@ApiParam(value = "id", required = true) @PathVariable String id);

    /**
     * 新增
     * @param dto dto
     * @return vo
     */
    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping("/")
    Result<V> add(@ApiParam(value = "dto", required = true) @RequestBody @Validated D dto);

    /**
     * 更新
     * @param id id
     * @param dto dto
     * @return vo
     */
    @ApiOperation(value = "更新", notes = "更新")
    @PutMapping("/{id}")
    Result<V> update(@ApiParam(value = "id", required = true) @PathVariable String id,
                     @ApiParam(value = "dto", required = true) @RequestBody @Validated D dto);

    /**
     * 删除
     * @param id id
     * @return 返回结果
     */
    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/{id}")
    Result delete(@ApiParam(value = "id", required = true) @PathVariable String id);

    /**
     * 列表查询
     * @param searchable 查询条件
     * @param sort 排序
     * @return 列表vo
     */
    @ApiOperation(value = "列表查询", notes = "列表查询")
    @PostMapping("/list")
    Result<List<V>> list(@ApiParam(value = "查询条件", required = true) @RequestBody S searchable,
                         @ApiParam(value = "排序", required = true) Sort sort);

    /**
     * 分页查询
     * api/customer/page?page=0&size=10&sort=code,desc&sort=name,desc
     * 分页参数：
     * page：第几页，默认为0，是第一页
     * size：分页大小，默认为20，在配置文件可以修改spring.data.controller.pageable.default-page-size
     * sort：排序字段，默认是asc排序方式，可以不写，格式：sort=code,asc&sort=name&sort=note,desc
     * @param searchable 查询条件
     * @param pageable 分页条件
     * @return 分页vo
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/page")
    Result<Page<V>> page(@ApiParam(value = "查询条件", required = true) @RequestBody S searchable,
                         @ApiParam(value = "分页条件", required = true) Pageable pageable);
}
