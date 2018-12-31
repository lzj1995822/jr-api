package com.cloudkeeper.leasing.base.service.impl;

import com.cloudkeeper.leasing.base.constant.AuthorizationConstants;
import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.base.utils.BeanConverts;
import com.cloudkeeper.leasing.base.utils.StrUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 基础service 实现
 * @param <T> 泛型
 * @author jerry
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    /** 实体manager */
    @Autowired
    protected EntityManager entityManager;

    /** JPA查询工厂 */
    protected JPAQueryFactory queryFactory;

    @Autowired
    protected HttpServletRequest request;

    /**
     * 子类实现该方法
     * @return IBaseRepository
     * @author jerry
     */
    protected abstract BaseRepository<T> getBaseRepository();

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 获取泛型类型
     * @return 泛型类型
     */
    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获取queryDsl T
     * @return queryDsl T
     */
    protected EntityPathBase<T> getEntityPath() {
        PathMetadata pathMetadata = PathMetadataFactory.forVariable(StrUtil.lowerCase(getEntityClass().getSimpleName()));
        return new EntityPathBase<>(getEntityClass(), pathMetadata);
    }

    @Override
    @Nonnull
    @Transactional(rollbackFor = Exception.class)
    public T save(@Nonnull T entity) {
        entity.setModifiedAt(null);
        entity.setModifiedBy(null);
        return getBaseRepository().save(entity);
    }

    @Nonnull
    @Override
    @Transactional(rollbackFor = Exception.class)
    public T saveAndFlush(@Nonnull T entity) {
        return getBaseRepository().saveAndFlush(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@Nonnull T entity) {
        getBaseRepository().delete(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(@Nonnull String id) {
        getBaseRepository().deleteById(id);
    }

    @Override
    public void deleteAll() {
        getBaseRepository().deleteAll();
    }

    @Override
    public T getOne(@Nonnull String id) {
        return getBaseRepository().getOne(id);
    }

    @Override
    public T findById(@Nonnull String id) {
        return findOptionalById(id).orElse(null);
    }

    @Override
    @Nonnull
    public Optional<T> findOptionalById(@Nonnull String id) {
        return getBaseRepository().findById(id);
    }

    @Override
    @Nonnull
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    @Nonnull
    public List<T> findAll(@Nonnull Sort sort) {
        return getBaseRepository().findAll(sort);
    }

    @Nonnull
    @Override
    public List<T> findAll(@Nonnull BaseSearchable searchable) {
        return findAll(defaultExample(searchable));
    }

    @Nonnull
    @Override
    public List<T> findAll(@Nonnull BaseSearchable searchable, @Nonnull Sort sort) {
        return findAll(defaultExample(searchable), sort);
    }

    @Override
    public BooleanBuilder defaultBooleanBuilder(@Nonnull BaseSearchable searchable) {
        return new BooleanBuilder();
    }

    @Override
    @Nonnull
    public <S extends T> List<S> findAll(@Nonnull Example<S> example) {
        return getBaseRepository().findAll(example);
    }

    @Override
    @Nonnull
    public <S extends T> List<S> findAll(@Nonnull Example<S> example, @Nonnull Sort sort) {
        return getBaseRepository().findAll(example, sort);
    }

    @Override
    @Nonnull
    public List<T> findAllById(@Nonnull Iterable<String> ids) {
        return getBaseRepository().findAllById(ids);
    }

    @Override
    @Nonnull
    public Page<T> findAll(@Nonnull Pageable pageable) {
        return getBaseRepository().findAll(pageable);
    }

    @Nonnull
    @Override
    public Page<T> findAll(@Nonnull BaseSearchable searchable, @Nonnull Pageable pageable) {
        return findAll(defaultExample(searchable), pageable);
    }

    @Override
    @Nonnull
    public Page<T> findAll(@Nonnull Example<T> example, @Nonnull Pageable pageable) {
        return getBaseRepository().findAll(example, pageable);
    }

    @Override
    @Nonnull
    public List<T> findAll(@Nonnull Specification<T> specification) {
        return getBaseRepository().findAll(specification);
    }

    @Override
    @Nonnull
    public Page<T> findAll(@Nonnull Specification<T> specification, @Nonnull Pageable pageable) {
        return getBaseRepository().findAll(specification, pageable);
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return ExampleMatcher.matching();
    }

    @Override
    public Example<T> defaultExample(@Nonnull BaseSearchable searchable) {
        return defaultExample(searchable, defaultExampleMatcher());
    }

    @Override
    public Example<T> defaultExample(@Nonnull BaseSearchable searchable, @Nonnull ExampleMatcher exampleMatcher) {
        Class<T> entityClass = getEntityClass();
        return Example.of(searchable.convert(entityClass), exampleMatcher);
    }

    public static final Map<Class<?>, Type> CLASS_TYPE_MAP = new HashMap<>();

    static {
        CLASS_TYPE_MAP.put(Integer.class, StandardBasicTypes.INTEGER);
        CLASS_TYPE_MAP.put(Long.class, StandardBasicTypes.LONG);
        CLASS_TYPE_MAP.put(Float.class, StandardBasicTypes.FLOAT);
        CLASS_TYPE_MAP.put(Double.class, StandardBasicTypes.DOUBLE);
        CLASS_TYPE_MAP.put(BigDecimal.class, StandardBasicTypes.BIG_DECIMAL);
        CLASS_TYPE_MAP.put(String.class, StandardBasicTypes.STRING);
        CLASS_TYPE_MAP.put(LocalDate.class, LocalDateType.INSTANCE);
        CLASS_TYPE_MAP.put(LocalDateTime.class, LocalDateTimeType.INSTANCE);
        CLASS_TYPE_MAP.put(LocalTime.class, LocalTimeType.INSTANCE);
    }

    @Override
    public Object transferUnique(@Nonnull Class<?> clazz, String sql) {
        Optional optional = getQuery(clazz, sql).uniqueResultOptional();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public NativeQuery getQuery(@Nonnull Class<?> clazz, String sql) {
        NativeQuery query = (NativeQuery) entityManager.createNativeQuery(sql);
        addMapping(query, clazz);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(clazz));
        return query;
    }

    @Override
    public <D> D findBySql(@Nonnull Class<D> clazz, @Nonnull String sql) {
        Object singleResult = entityManager.createNativeQuery(sql).unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getSingleResult();
        if (singleResult == null) {
            return null;
        }
        return BeanConverts.mapToObj(clazz, (Map<String, Object>) singleResult);
    }

    @Override
    public <D> List<D> findAllBySql(@Nonnull Class<D> clazz, @Nonnull String sql) {
        List<Map<String, Object>> list = entityManager.createNativeQuery(sql).unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        return BeanConverts.mapToObj(clazz, list);
    }

    @Override
    public Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value) {
        return getValueMap(key, value, null, null);
    }

    @Override
    public Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, Predicate predicate) {
        return getValueMap(key, value, predicate, null);
    }

    @Override
    public Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, OrderSpecifier<?> orders) {
        return getValueMap(key, value, null, orders);
    }

    @Override
    public Map<String, String> getValueMap(@Nonnull Path<String> key, @Nonnull Path<String> value, Predicate predicate, OrderSpecifier<?> orders) {
        EntityPathBase entityPathBase = new EntityPathBase<>(key.getRoot().getType(), key.getRoot().getMetadata());
        JPAQuery<Tuple> jpaQuery = queryFactory.select(key, value).from(entityPathBase).where(predicate);
        if (predicate != null) {
            jpaQuery.where(predicate);
        }
        if (orders != null) {
            jpaQuery.orderBy(orders);
        }
        List<Tuple> tupleList = jpaQuery.fetch();
        Map<String, String> map = new LinkedHashMap<>();
        for (Tuple tuple : tupleList) {
            map.put(tuple.get(key), tuple.get(value));
        }
        return map;
    }

    @Override
    public Map<String, String> getValueMap(@Nonnull String sql) {
        List<Object[]> list = entityManager.createNativeQuery(sql).getResultList();
        Map<String, String> map = new LinkedHashMap<>();
        for (Object[] objects : list) {
            map.put(objects[0].toString(), objects[1].toString());
        }
        return map;
    }

    /**
     * 添加 字段类型
     * @param query sql查询对象
     * @param clazz 类
     */
    private void addMapping(NativeQuery query, @Nonnull Class clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            query.addScalar(field.getName(), CLASS_TYPE_MAP.get(field.getType()));
        }
        if (clazz.getSuperclass() != null) {
            addMapping(query, clazz.getSuperclass());
        }
    }


    @Override
    public void saveHistoryList(List<T> list) {
        list.forEach(item -> insertHis(item.getId()));
    }

    @Override
    public void deleteList(List<String> idList) {
        idList.forEach(id -> {
            deleteHis(id);
            deleteById(id);
        });
    }

    @Override
    public int insertHis(@Nonnull String id) {
        String tableName = getTableName();
        String sql = String.format("INSERT INTO %s_his SELECT UUID() as pkId, a.* FROM (SELECT * from %s where id = '%s') a", tableName, tableName, id);
        NativeQuery query = (NativeQuery) entityManager.createNativeQuery(sql);
        return query.executeUpdate();
    }

    @Override
    public <R> R getHis(@Nonnull Class<R> clazz, @Nonnull String hisId) {
        String sql = String.format("SELECT * FROM %s_his WHERE pkId = '%s'", getTableName(), hisId);
        return (R) transferUnique(clazz, sql);

    }

    @Override
    public <R> R getHis(@Nonnull Class<R> clazz, @Nonnull String id, @Nonnull Integer version) {
        String sql = String.format("SELECT * FROM %s_his WHERE id = '%s' AND version = %s", getTableName(), id, version);
        return (R) transferUnique(clazz, sql);
    }

    @Override
    public <R> R getLatestHisByHisId(@Nonnull Class<R> clazz, @Nonnull String hisId) {
        String tableName = getTableName();
        String sql = String.format("SELECT * FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                "WHERE e.id = (SELECT id FROM %s_his WHERE pkId = '%s')", tableName, tableName, tableName, hisId);
        return (R) transferUnique(clazz, sql);
    }


    @Override
    public <R> List<R> getLatestHisListByHisId(@Nonnull Class<R> clazz, @Nonnull List<String> hisIdList) {
        String tableName = getTableName();
        String sql = String.format("SELECT * FROM %s_his h LEFT JOIN %s e ON e.id = h.id AND e.version = h.version " +
                "WHERE e.id in (SELECT id FROM %s_his WHERE pkId in (:hisIdList))", tableName, tableName, tableName);
        NativeQuery query = getQuery(clazz, sql).setParameter("hisIdList", hisIdList);
        List<R> list = query.getResultList();
        return list;
    }

    @Override
    public int deleteHis(@Nonnull String id) {
        String sql = String.format("DELETE FROM %s_his WHERE id = '%s'", getTableName(), id);
        NativeQuery query = (NativeQuery) entityManager.createNativeQuery(sql);
        return query.executeUpdate();
    }

    @Override
    public String getTableName() {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //返回表示此类型实际类型参数的 Type 对象的数组()
        Class clazz = (Class) type.getActualTypeArguments()[0];
        return ((Table) clazz.getAnnotation(Table.class)).name();
    }

    @Override
    public List<T> saveList(@Nonnull List<T> list) {
        return list.stream().map(this::save).collect(Collectors.toList());
    }

    @Override
    @Nonnull
    public Optional<T> findOne(Predicate predicate) {
        return getBaseRepository().findOne(predicate);
    }

    @Override
    @Nonnull
    public Iterable<T> findAll(Predicate predicate) {
        return getBaseRepository().findAll(predicate);
    }

    @Override
    @Nonnull
    public Iterable<T> findAll(Predicate predicate, Sort sort) {
        return getBaseRepository().findAll(predicate, sort);
    }

    @Override
    @Nonnull
    public Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return getBaseRepository().findAll(predicate, orders);
    }

    @Override
    @Nonnull
    public Iterable<T> findAll(OrderSpecifier<?>... orders) {
        return getBaseRepository().findAll(orders);
    }

    @Override
    @Nonnull
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return getBaseRepository().findAll(predicate, pageable);
    }

    @Override
    public long count(Predicate predicate) {
        return getBaseRepository().count(predicate);
    }

    @Override
    public boolean exists(Predicate predicate) {
        return getBaseRepository().exists(predicate);
    }

    @Override
    public NativeQuery getNativeQuery(@Nonnull String sql) {
        return (NativeQuery) entityManager.createNativeQuery(sql);
    }

    @Override
    public Object getCurrentPrincipal() {
        return getBaseRepository().findById((String) request.getSession().getAttribute(AuthorizationConstants.CURRENT_USER_ID));
    }

    @Override
    public <R> R findOneBySql(@Nonnull Class<R> clazz, @Nonnull String sql) {
        NativeQuery query = getQuery(clazz, sql);
        R r = (R) query.uniqueResult();
        return r;
    }

    @Override
    public <R> List<R> findAllListBySql(@Nonnull Class<R> clazz, @Nonnull String sql) {
        NativeQuery query = getQuery(clazz, sql);
        List<R> list = query.getResultList();
        return list;
    }



}
