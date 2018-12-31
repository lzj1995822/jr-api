package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * 用户 repository
 * @author jerry
 */
@Repository
public interface PrincipalRepository extends BaseRepository<Principal> {

    /**
     * 查询用户
     * @param code 登录名
     * @return 用户
     */
    Optional<Principal> findByCode(@Nonnull String code);

    /**
     * 是否存在
     * @param code 编码
     * @param id 主键
     * @return true 存在
     */
    boolean existsByCodeAndIdNot(@Nonnull String code, @Nonnull String id);

    /**
     * 是否存在
     * @param code 编码
     * @return true 存在
     */
    boolean existsByCode(@Nonnull String code);

    //历史纪录备份
//    @Modifying
//    @Query(value = "INSERT INTO ck_id_principal_hi select * from ck_id_principal where id = ?1", nativeQuery = true)
//    @Transactional
//    int insertHis(String id);

    List<Principal> findAllByOrgIdIn(List<String> orgIdList);
}
