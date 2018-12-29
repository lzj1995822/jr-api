package com.cloudkeeper.leasing.base.service;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import org.hibernate.query.NativeQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基础service
 * @param <T> 实体泛型
 * @author jerry
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 保存/更新
     * @param entity 实体
     * @return 实体
     */
    @Nonnull
    T save(@Nonnull T entity);

    /**
     * 保存/更新，立即刷新到数据库
     * @param entity 实体
     * @return 实体
     */
    @Nonnull
    T saveAndFlush(@Nonnull T entity);

    /**
     * 删除
     * @param entity 实体
     */
    void delete(@Nonnull T entity);

    /**
     * 删除
     * @param id 实体id
     */
    void deleteById(@Nonnull String id);

    /**
     * 删除所有
     */
    void deleteAll();

    /**
     * 查询实体
     * @param id 实体id
     * @return 实体 未加载数据库数据
     */
    T getOne(@Nonnull String id);

    /**
     * 查询实体
     * @param id 实体id
     * @return 实体
     */
    T findById(@Nonnull String id);

    /**
     * 查询实体
     * @param id 实体id
     * @return 实体
     */
    @Nonnull
    Optional<T> findOptionalById(@Nonnull String id);

    /**
     * 查询列表
     * @return 实体集合
     */
    @Nonnull
    List<T> findAll();

    /**
     * 查询列表
     * @param sort 排序
     * @return 实体集合
     */
    @Nonnull
    List<T> findAll(@Nonnull Sort sort);

    /**
     * 根据id集合，查询实体列表
     * @param ids id集合
     * @return 实体集合
     */
    @Nonnull
    List<T> findAllById(@Nonnull Iterable<String> ids);

    /**
     * 查询列表
     * @param searchable 查询条件
     * @return 实体集合
     */
    @Nonnull
    List<T> findAll(@Nonnull BaseSearchable searchable);

    /**
     * 查询列表
     * @param searchable 查询条件
     * @param sort 排序
     * @return 实体集合
     */
    @Nonnull
    List<T> findAll(@Nonnull BaseSearchable searchable, @Nonnull Sort sort);

    /**
     * 查询列表
     * @param example 查询条件
     * @param <S> 泛型
     * @return 实体集合
     */
    @Nonnull
    <S extends T> List<S> findAll(@Nonnull Example<S> example);

    /**
     * 查询列表
     * @param example 查询条件
     * @param sort 排序
     * @param <S> 泛型
     * @return 实体集合
     */
    @Nonnull
    <S extends T> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort);

    /**
     * 查询分页
     * @param pageable 分页条件
     * @return 分页实体
     */
    @Nonnull
    Page<T> findAll(@Nonnull Pageable pageable);

    /**
     * 查询分页
     * @param searchable 查询条件
     * @param pageable 分页条件
     * @return 分页实体
     */
    @Nonnull
    Page<T> findAll(@Nonnull BaseSearchable searchable, @Nonnull Pageable pageable);

    /**
     * 查询分页
     * @param example 查询条件
     * @param pageable 分页条件
     * @return 分页实体
     */
    @Nonnull
    Page<T> findAll(@Nonnull Example<T> example, @Nonnull Pageable pageable);

    /**
     * 查询列表
     * @param specification 查询条件
     * @return 实体集合
     */
    @Nonnull
    List<T> findAll(@Nonnull Specification<T> specification);

    /**
     * 查询分页
     * @param specification 查询条件
     * @param pageable 分页条件
     * @return 分页实体
     */
    @Nonnull
    Page<T> findAll(@Nonnull Specification<T> specification, @Nonnull Pageable pageable);

    /**
     * 默认查询规则
     * @return 查询规则
     */
    ExampleMatcher defaultExampleMatcher();

    /**
     * 默认查询条件
     * @param searchable 查询条件 dto
     * @return 查询条件
     */
    Example<T> defaultExample(@Nonnull BaseSearchable searchable);

    /**
     * 默认查询条件
     * @param searchable 查询条件 dto
     * @param exampleMatcher 查询规则
     * @return 查询条件
     */
    Example<T> defaultExample(@Nonnull BaseSearchable searchable, @Nonnull ExampleMatcher exampleMatcher);

    /**
     * 默认BooleanBuilder查询对象
     * @param searchable
     * @return
     */
    BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable);

    /**
     * 获取第一条记录并封装成VO对象
     * @param clazz vo类
     * @param sql sql语句
     * @return vo对象
     */
    Object transferUnique(@Nonnull Class<?> clazz,@Nonnull String sql);

    /**
     * 生成历史记录
     * @param id 主键id
     * @return 插入数量
     */
    int insertHis(@Nonnull String id);

    /**
     * 获取指定主键id的历史记录
     * @param clazz 接受对象类型
     * @param hisId 历史主键id
     * @return
     */
    <R> R getHis(@Nonnull Class<R> clazz, @Nonnull String hisId);

    /**
     * 根据业务id和指定版本获取历史记录
     * @param clazz 接受对象类型
     * @param id 业务主键id
     * @param version 指定版本
     * @return
     */
    <R> R getHis(@Nonnull Class<R> clazz, @Nonnull String id, @Nonnull Integer version);

    /**
     * 根据某一历史记录id获取最新的历史记录
     * @param clazz 接受类
     * @param hisId 历史记录id
     * @param <R> 返回类型
     * @return
     */
    <R> R getLatestHisByHisId(@Nonnull Class<R> clazz, @Nonnull String hisId);

    /**
     * 根据某一历史记录集合获取最新的历史记录集合
     * @param clazz 接受类
     * @param <R> 返回类型
     * @return
     */
    <R> List<R> getLatestHisListByHisId(@Nonnull Class<R> clazz, @Nonnull List<String> hisIdList);

    /**
     * 删除某一业务类记录所有历史记录
     * @param id 主键id
     * @return 删除数量
     */
    int deleteHis(@Nonnull String id);

    /**
     * 批量保存或更新
     * @param list 实体类集合
     */
    void saveHistoryList(List<T> list);

    /**
     * 获取原生查询query对象
     * @param clazz vo类
     * @param sql 查询语句
     * @return sql查询条件
     */
    NativeQuery getQuery(@Nonnull Class<?> clazz, String sql);

    /**
     * 对象查询
     * 原生sql查询
     * @param clazz 结果类型
     * @param sql sql
     * @param <D> 泛型
     * @return 集合
     */
    <D> D findBySql(@Nonnull Class<D> clazz, @Nonnull String sql);

    /**
     * 列表查询
     * 原生sql查询
     * @param clazz 结果类型
     * @param sql sql
     * @param <D> 泛型
     * @return 集合
     */
    <D> List<D> findAllBySql(@Nonnull Class<D> clazz, @Nonnull String sql);

    /**
     * 获取字段值 键值对
     * @param key key字段
     * @param value value字段
     * @return 键值对集合
     */
    Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value);

    /**
     * 获取字段值 键值对
     * @param key key字段
     * @param value value字段
     * @param predicate 查询条件
     * @return 键值对集合
     */
    Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, Predicate predicate);

    /**
     * 获取字段值 键值对
     * @param key key字段
     * @param value value字段
     * @param orders 排序条件
     * @return 键值对集合
     */
    Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, OrderSpecifier<?> orders);

    /**
     * 获取字段值 键值对
     * @param key key字段
     * @param value value字段
     * @param predicate 查询条件
     * @param orders 排序条件
     * @return 键值对集合
     */
    Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, Predicate predicate, OrderSpecifier<?> orders);

    /**
     * 获取字段值 键值对
     * @param sql sql语句
     * @return 键值对集合
     */
    Map<String, String> getValueMap(@Nonnull String sql);

    /**
     * 批量删除(包含对历史记录的删除)
     * @param idList id集合
     */
    void deleteList(List<String> idList);


    String getTableName();

    /**
     * 批量更新或保存实体类集合
     * @param list 实体集合
     * @return 实体集合
     */
    List<T> saveList(@Nonnull List<T> list);

    /**
     * Returns a single entity matching the given {@link Predicate} or {@link Optional#empty()} if none was found.
     * @param predicate must not be {@literal null}.
     * @return a single entity matching the given {@link Predicate} or {@link Optional#empty()} if none was found.
     * @throws org.springframework.dao.IncorrectResultSizeDataAccessException if the predicate yields more than one
     * result.
     */
    @Nonnull
    Optional<T> findOne(Predicate predicate);

    /**
     * Returns all entities matching the given {@link Predicate}. In case no match could be found an empty
     * {@link Iterable} is returned.
     * @param predicate must not be {@literal null}.
     * @return all entities matching the given {@link Predicate}.
     */
    @Nonnull
    Iterable<T> findAll(Predicate predicate);

    /**
     * Returns all entities matching the given {@link Predicate} applying the given {@link Sort}. In case no match could
     * be found an empty {@link Iterable} is returned.
     * @param predicate must not be {@literal null}.
     * @param sort the {@link Sort} specification to sort the results by, may be {@link Sort#empty()}, must not be
     * {@literal null}.
     * @return all entities matching the given {@link Predicate}.
     * @since 1.10
     */
    @Nonnull
    Iterable<T> findAll(Predicate predicate, Sort sort);

    /**
     * Returns all entities matching the given {@link Predicate} applying the given {@link OrderSpecifier}s. In case no
     * match could be found an empty {@link Iterable} is returned.
     * @param predicate must not be {@literal null}.
     * @param orders the {@link OrderSpecifier}s to sort the results by.
     * @return all entities matching the given {@link Predicate} applying the given {@link OrderSpecifier}s.
     */
    @Nonnull
    Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    /**
     * Returns all entities ordered by the given {@link OrderSpecifier}s.
     * @param orders the {@link OrderSpecifier}s to sort the results by.
     * @return all entities ordered by the given {@link OrderSpecifier}s.
     */
    @Nonnull
    Iterable<T> findAll(OrderSpecifier<?>... orders);

    /**
     * Returns a {@link Page} of entities matching the given {@link Predicate}. In case no match could be found, an empty
     * {@link Page} is returned.
     * @param predicate must not be {@literal null}.
     * @param pageable may be {@link Pageable#unpaged()}, must not be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Predicate}.
     */
    @Nonnull
    Page<T> findAll(Predicate predicate, Pageable pageable);

    /**
     * Returns the number of instances matching the given {@link Predicate}.
     * @param predicate the {@link Predicate} to count instances for, must not be {@literal null}.
     * @return the number of instances matching the {@link Predicate}.
     */
    long count(Predicate predicate);

    /**
     * Checks whether the data store contains elements that match the given {@link Predicate}.
     * @param predicate the {@link Predicate} to use for the existence check, must not be {@literal null}.
     * @return {@literal true} if the data store contains elements that match the given {@link Predicate}.
     */
    boolean exists(Predicate predicate);

    /**
     * 根据sql获取原生查询对象
     * @param sql
     * @return
     */
    NativeQuery getNativeQuery(@Nonnull String sql);

    /**
     * 获取当前登录用户
     * @return
     */
    Object getCurrentPrincipal();
}
