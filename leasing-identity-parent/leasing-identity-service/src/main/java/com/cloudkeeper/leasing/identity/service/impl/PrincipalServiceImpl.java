package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.constant.BaseConstants;
import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.RedisService;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.constant.ProcessConstants;
import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.identity.domain.PrincipalOrganization;
import com.cloudkeeper.leasing.identity.domain.QPrincipal;
import com.cloudkeeper.leasing.identity.domain.QPrincipalOrganization;
import com.cloudkeeper.leasing.identity.dto.principal.PrincipalLoginDTO;
import com.cloudkeeper.leasing.identity.dto.principal.PrincipalSearchable;
import com.cloudkeeper.leasing.identity.repository.PrincipalRepository;
import com.cloudkeeper.leasing.identity.service.*;
import com.cloudkeeper.leasing.identity.vo.OrganizationVO;
import com.cloudkeeper.leasing.identity.vo.PrincipalVO;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户 service
 * @author jerry
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PrincipalServiceImpl extends BaseServiceImpl<Principal> implements PrincipalService {

    /** 用户 repository */
    private final PrincipalRepository principalRepository;

    /** 用户组织 service */
    private final PrincipalOrganizationService principalOrganizationService;

    /** 组织 service */
    private final OrganizationService organizationService;

    /** 角色组织 service */
    private final RoleMenuService roleMenuService;

    private final OrgCenterService orgCenterService;

    private final OrgRoomService orgRoomService;

    private final CountryService countryService;

    private final RoleService roleService;

    /** redis service */
    private final RedisService redisService;

    @Override
    protected BaseRepository<Principal> getBaseRepository() {
        return principalRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public Result<String> login(@Nonnull PrincipalLoginDTO principalLoginDTO) {
        Optional<Principal> principalOptional = principalRepository.findByCode(principalLoginDTO.getCode());
        if (!principalOptional.isPresent()) {
            return Result.of(Result.ResultCode.LOGIN_FAIL.getCode(), "用户名或密码错误！");
        }
        Principal principal = principalOptional.get();
        if (!principal.getPassword().equals(DigestUtils.md5Hex(principalLoginDTO.getPassword()))) {
            return Result.of(Result.ResultCode.LOGIN_FAIL.getCode(), "用户名或密码错误！");
        }
        if (BaseConstants.Boolean.TRUE.ordinal() == principal.getIsDelete()) {
            return Result.of(Result.ResultCode.LOGIN_FAIL.getCode(), "用户名已被禁用！");
        }
        String token = redisService.putToken(principal.getId());
        token += "," + principal.getId();
        return Result.of("登录成功！", token);
    }

    @Override
    public boolean existsCode(@Nonnull String code, String id) {
        if (StringUtils.hasText(id)) {
            return principalRepository.existsByCodeAndIdNot(code, id);
        } else {
            return principalRepository.existsByCode(code);
        }
    }

    @Override
    public Principal updateIsDelete(@Nonnull String id, @Nonnull Integer isDelete) {
        Optional<Principal> principalOptional = principalRepository.findById(id);
        if (!principalOptional.isPresent()) {
            return null;
        }
        Principal principal = principalOptional.get();
        principal.setIsDelete(isDelete);
        return principalRepository.save(principal);
    }

    @Nonnull
    @Override
    public Page<Principal> findAll(@Nonnull BaseSearchable searchable, @Nonnull Pageable pageable) {
        PrincipalSearchable principalSearchable = (PrincipalSearchable) searchable;
        QPrincipal qPrincipal = QPrincipal.principal;
        QPrincipalOrganization qPrincipalOrganization = QPrincipalOrganization.principalOrganization;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(principalSearchable.getCode())) {
            booleanBuilder.and(qPrincipal.code.contains(principalSearchable.getCode()));
        }
        if (StringUtils.hasText(principalSearchable.getName())) {
            booleanBuilder.and(qPrincipal.name.contains(principalSearchable.getName()));
        }
        if (StringUtils.hasText(principalSearchable.getOrganizationCode())) {
            booleanBuilder.and(qPrincipal.id.in(JPAExpressions.select(qPrincipalOrganization.principalId).from(qPrincipalOrganization)
                    .where(qPrincipalOrganization.organization.fullCode.contains(principalSearchable.getOrganizationCode()))));
        }
        return findAll(booleanBuilder, pageable);
    }

    @Override
    public void loadChildrenVO(@Nonnull PrincipalVO principalVO) {
        // 主岗
        PrincipalOrganization principalOrganization = principalOrganizationService.findByPrincipalId(principalVO.getId());
        if (principalOrganization != null) {
            OrganizationVO organizationVO = principalOrganization.getOrganization().convert(OrganizationVO.class);
            organizationService.loadChildrenVO(organizationVO);
            principalVO.setOrganizationVO(organizationVO);
        }
        // 兼职岗
        List<OrganizationVO> organizationVOList = principalOrganizationService.findAllByPrincipalId(principalVO.getId())
                .stream()
                .filter(principalOrganization1 -> principalOrganization1.getIsPart() == BaseConstants.Boolean.TRUE.ordinal())
                .map(principalOrganization1 -> {
                    OrganizationVO organizationVO = principalOrganization1.getOrganization().convert(OrganizationVO.class);
                    organizationService.loadChildrenVO(organizationVO);
                    return organizationVO;
                })
                .collect(Collectors.toList());
        principalVO.setOrganizationVOList(organizationVOList);
        // 菜单
        List<String> menuCodeList = roleMenuService.findAllMenuCodeByPrincipalId(principalVO.getId());
        principalVO.setMenuCodeList(menuCodeList);
    }

    @Nonnull
    @Override
    public List<Principal> findAllByOrganizationPosition(@Nonnull String organizationId) {
        QPrincipalOrganization qPrincipalOrganization = QPrincipalOrganization.principalOrganization;
        Predicate predicate = QPrincipal.principal.id.in(JPAExpressions.select(qPrincipalOrganization.principalId).from(qPrincipalOrganization).where(qPrincipalOrganization.organizationId.eq(organizationId)));
        return Lists.newArrayList(super.findAll(predicate));
    }

    @Override
    public Optional<Principal> findByOrganizationPosition(@Nonnull String organizationId) {
        return findAllByOrganizationPosition(organizationId).stream().findFirst();
    }

    @Override
    public Optional<Principal> findLeaderById(@Nonnull String id) {
        PrincipalOrganization principalOrganization = principalOrganizationService.findByPrincipalId(id);
        Optional<Principal> principalOptional = Optional.empty();
        if (principalOrganization != null && StringUtils.hasText(principalOrganization.getOrganization().getParentPositionId())) {
            principalOptional = this.findByOrganizationPosition(principalOrganization.getOrganization().getParentPositionId());
        }
        return principalOptional;
    }

    @Override
    public List<Principal> findAllByOrgIdIn(List<String> orgIdList) {
        return principalRepository.findAllByOrgIdIn(orgIdList);
    }

    @Override
    public List<PrincipalVO> toVoList(List<Principal> principals) {
        List<PrincipalVO> principalVOS = new ArrayList<>();
        principals.stream().forEach(principal -> {
            PrincipalVO principalVO = new PrincipalVO();
            BeanUtils.copyProperties(principal, principalVO);
            principalVO.setRoleName(roleService.findById(principal.getRoleId()).getName());
            if (ProcessConstants.ORG_CENTER.equals(principal.getType())) {
                principalVO.setOrgName(orgCenterService.findById(principal.getOrgId()).getName());
            } else if (ProcessConstants.ORG_ROOM.equals(principal.getType())){
                principalVO.setOrgName(orgRoomService.findById(principal.getOrgId()).getName());
            } else if (ProcessConstants.ORG_COUNTRY.equals(principal.getType())){
                principalVO.setOrgName(countryService.findById(principal.getOrgId()).getName());
            } else {
                principalVO.setOrgName("");
            }
            principalVOS.add(principalVO);
        });
        return principalVOS;
    }
}
