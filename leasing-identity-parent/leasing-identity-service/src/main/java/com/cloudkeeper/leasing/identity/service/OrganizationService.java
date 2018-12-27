package com.cloudkeeper.leasing.identity.service;


import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.vo.OrganizationVO;
import com.cloudkeeper.leasing.base.service.BaseService;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

/**
 * 组织 service
 * @author jerry
 */
public interface OrganizationService extends BaseService<Organization> {

    /**
     * 获取组织树
     * @return 组织树
     */
    OrganizationVO findTree();

    /**
     * 根据id，查询子节点
     * @param parentId 父节点id
     * @return 组织
     */
    @Nonnull
    List<Organization> findAllByParentId(@Nonnull String parentId);

    /**
     * 组织编码，是否存在
     * @param code 编码
     * @param parentId 父id
     * @param id id
     * @return true 存在
     */
    boolean existsCode(@Nonnull String code, @Nonnull String parentId, String id);

    /**
     * 加载 子类vo数据
     * @param organizationVO 组织vo
     */
    void loadChildrenVO(@Nonnull OrganizationVO organizationVO);

    /**
     * 查询分公司
     * @param id 岗位id
     * @return 分公司
     */
    Optional<Organization> findBranchById(@Nonnull String id);

    /**
     * 查询分公司
     * @param principalId 用户id
     * @return 分公司
     */
    Optional<Organization> findBranchByPrincipalId(@Nonnull String principalId);

    /**
     * 查询部门
     * @param id 岗位id
     * @return 部门
     */
    Optional<Organization> findDeptById(@Nonnull String id);

    /**
     * 查询部门
     * @param principalId 用户id
     * @return 部门
     */
    Optional<Organization> findDeptByPrincipalId(@Nonnull String principalId);

    /**
     * 查询职位
     * @param principalId 用户id
     * @return 职位
     */
    Optional<Organization> findPositionByPrincipalId(@Nonnull String principalId);

    /**
     * 可选上级职位列表查询
     * @param id 组织id（岗位/部门）
     * @return 职位集合
     */
    List<Organization> listParentPosition(@Nonnull String id);
}
