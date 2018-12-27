package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.OrganizationConstants;
import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.domain.PrincipalOrganization;
import com.cloudkeeper.leasing.identity.enumeration.OrganizationTypeEnum;
import com.cloudkeeper.leasing.identity.repository.OrganizationRepository;
import com.cloudkeeper.leasing.identity.service.OrganizationRoleService;
import com.cloudkeeper.leasing.identity.service.OrganizationService;
import com.cloudkeeper.leasing.identity.service.PrincipalOrganizationService;
import com.cloudkeeper.leasing.identity.service.RoleService;
import com.cloudkeeper.leasing.identity.vo.OrganizationVO;
import com.cloudkeeper.leasing.identity.vo.RoleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 组织 service
 * @author jerry
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService {

    /** 组织 repository */
    private final OrganizationRepository organizationRepository;

    /** 组织角色 service */
    private final OrganizationRoleService organizationRoleService;

    /** 角色 service */
    private final RoleService roleService;

    /** 用户组织 service */
    private final PrincipalOrganizationService principalOrganizationService;

    @Override
    protected BaseRepository<Organization> getBaseRepository() {
        return organizationRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    public Organization save(@Nonnull Organization entity) {
        String fullCode = "";
        if (StringUtils.hasText(entity.getParentId())) {
            Optional<Organization> organizationOptional = findOptionalById(entity.getParentId());
            fullCode = organizationOptional.map(Organization::getFullCode).orElse("");
        }
        entity.setFullCode(fullCode + "-" + entity.getCode());
        return super.save(entity);
    }

    @Override
    public OrganizationVO findTree() {
        Organization organization = organizationRepository.findByCode(OrganizationConstants.CODE_ROOT);
        OrganizationVO organizationVO = organization.convert(OrganizationVO.class);
        organizationVO.getChildren().addAll(findAllVOByParentId(organization.getId()));
        return organizationVO;
    }

    @Nonnull
    private List<OrganizationVO> findAllVOByParentId(@Nonnull String parentId) {
        List<Organization> organizationList = findAllByParentId(parentId);
        List<OrganizationVO> organizationVOList = Organization.convert(organizationList, OrganizationVO.class);
        organizationVOList.forEach(organizationVO -> organizationVO.getChildren().addAll(findAllVOByParentId(organizationVO.getId())));
        return organizationVOList;
    }

    @Nonnull
    @Override
    public List<Organization> findAllByParentId(@Nonnull String parentId) {
        return organizationRepository.findAllByParentIdOrderBySortAsc(parentId);
    }

    @Override
    public boolean existsCode(@Nonnull String code, @Nonnull String parentId, String id) {
        if (StringUtils.hasText(id)) {
            return organizationRepository.existsByParentIdAndCodeAndIdNot(parentId, code, id);
        } else {
            return organizationRepository.existsByParentIdAndCode(parentId, code);
        }
    }

    @Override
    public void loadChildrenVO(@Nonnull OrganizationVO organizationVO) {
        List<RoleVO> roleVOList = organizationRoleService.findAllByOrganizationId(organizationVO.getId())
                .stream()
                .map(organizationRole -> {
                    RoleVO roleVO = organizationRole.getRole().convert(RoleVO.class);
                    roleService.loadChildrenVO(roleVO);
                    return roleVO;
                })
                .collect(Collectors.toList());
        organizationVO.setRoleVOList(roleVOList);
    }

    @Override
    public Optional<Organization> findBranchById(@Nonnull String id) {
        Optional<Organization> optional = super.findOptionalById(id);
        if (!optional.isPresent() || OrganizationTypeEnum.COMPANY.equals(optional.get().getType())) {
            return optional;
        }
        return findBranchById(optional.get().getParentId());
    }

    @Override
    public Optional<Organization> findBranchByPrincipalId(@Nonnull String principalId) {
        Optional<Organization> organizationOptional = findPositionByPrincipalId(principalId);
        if (organizationOptional.isPresent() && !OrganizationTypeEnum.COMPANY.equals(organizationOptional.get().getType()) && StringUtils.hasText(organizationOptional.get().getParentId())) {
            organizationOptional = findBranchById(organizationOptional.get().getParentId());
        }
        return organizationOptional;
    }

    @Override
    public Optional<Organization> findDeptById(@Nonnull String id) {
        Optional<Organization> optional = super.findOptionalById(id);
        if (!optional.isPresent() || OrganizationTypeEnum.DEPT.equals(optional.get().getType())) {
            return optional;
        }
        return findDeptById(optional.get().getParentId());
    }

    @Override
    public Optional<Organization> findDeptByPrincipalId(@Nonnull String principalId) {
        Optional<Organization> organizationOptional = findPositionByPrincipalId(principalId);
        if (organizationOptional.isPresent() && !OrganizationTypeEnum.DEPT.equals(organizationOptional.get().getType()) && StringUtils.hasText(organizationOptional.get().getParentId())) {
            organizationOptional = findDeptById(organizationOptional.get().getParentId());
        }
        return organizationOptional;
    }

    @Override
    public Optional<Organization> findPositionByPrincipalId(@Nonnull String principalId) {
        PrincipalOrganization principalOrganization = principalOrganizationService.findByPrincipalId(principalId);
        if (principalOrganization == null) {
            return Optional.empty();
        }
        return super.findOptionalById(principalOrganization.getOrganizationId());
    }

    @Override
    public List<Organization> listParentPosition(@Nonnull String id) {
        Optional<Organization> organizationOptional = super.findOptionalById(id);
        if (!organizationOptional.isPresent()) {
            return new ArrayList<>();
        }
        Organization organization = organizationOptional.get();
        String parentId;
        if (OrganizationTypeEnum.POSITION.equals(organization.getType())) {
            parentId = organization.getParentId();
        } else {
            parentId = organization.getId();
            id = "";
        }
        return organizationRepository.findAllByParentIdAndTypeAndIdNot(parentId, OrganizationTypeEnum.POSITION, id);
    }
}
