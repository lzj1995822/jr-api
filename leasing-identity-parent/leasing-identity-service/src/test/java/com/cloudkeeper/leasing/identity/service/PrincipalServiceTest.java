package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.base.constant.BaseConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.identity.domain.PrincipalOrganization;
import com.cloudkeeper.leasing.identity.dto.principal.PrincipalLoginDTO;
import com.cloudkeeper.leasing.identity.enumeration.OrganizationTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrincipalServiceTest {

    /** 用户 service */
    @Autowired
    private PrincipalService principalService;

    /** 用户组织 service */
    @Autowired
    private PrincipalOrganizationService principalOrganizationService;

    /** 组织 service */
    @Autowired
    private OrganizationService organizationService;

    private static Principal PRINCIPAL;

    private static final String TEST_CODE = "test";
    private static final String TEST_CODE_DELETE = "testDelete";
    private static final String TEST_PASSWORD = "123456";

    @Before
    public void before() {
        PRINCIPAL = new Principal().setCode(TEST_CODE).setPassword(DigestUtils.md5Hex(TEST_PASSWORD));
        PRINCIPAL = principalService.save(PRINCIPAL);
    }

    @After
    public void after() {
        principalOrganizationService.deleteAll();
        organizationService.deleteAll();
        principalService.deleteAll();
    }

    @Test
    public void login() {
        Result<String> result;
        result = principalService.login(new PrincipalLoginDTO().setCode(TEST_CODE).setPassword(TEST_PASSWORD));
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());

        result = principalService.login(new PrincipalLoginDTO().setCode(TEST_CODE + "1").setPassword(TEST_PASSWORD));
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.LOGIN_FAIL.getCode());

        result = principalService.login(new PrincipalLoginDTO().setCode(TEST_CODE).setPassword(TEST_PASSWORD + "1"));
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.LOGIN_FAIL.getCode());

        result = principalService.login(new PrincipalLoginDTO().setCode(TEST_CODE_DELETE).setPassword(TEST_PASSWORD));
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.LOGIN_FAIL.getCode());
    }

    @Test
    public void existsCode() {
        assertThat(principalService.existsCode(TEST_CODE, PRINCIPAL.getId() + "1")).isTrue();
        assertThat(principalService.existsCode(TEST_CODE, PRINCIPAL.getId())).isFalse();
        assertThat(principalService.existsCode(TEST_CODE, null)).isTrue();
        assertThat(principalService.existsCode(TEST_CODE + "1", null)).isFalse();
    }

    @Test
    public void updateIsDelete() {
        Principal principal = principalService.updateIsDelete(PRINCIPAL.getId(), BaseConstants.Boolean.TRUE.ordinal());
        assertThat(principal.getIsDelete()).isEqualTo(BaseConstants.Boolean.TRUE.ordinal());
    }

    @Test
    public void loadChildrenVO() {
    }

    @Test
    public void findAllByOrganizationPosition() {
        List<Principal> principalList = principalService.findAllByOrganizationPosition(findByOrganizationPositionInit());
        assertThat(principalList).hasSize(2);
    }

    private String findByOrganizationPositionInit() {
        Organization organization = new Organization().setType(OrganizationTypeEnum.POSITION);
        organizationService.save(organization);
        Principal principal1 = new Principal().setCode("1");
        Principal principal2= new Principal().setCode("2");
        principalService.save(principal1);
        principalService.save(principal2);
        principalOrganizationService.save(new PrincipalOrganization().setPrincipalId(principal1.getId()).setOrganizationId(organization.getId()));
        principalOrganizationService.save(new PrincipalOrganization().setPrincipalId(principal2.getId()).setOrganizationId(organization.getId()));
        return organization.getId();
    }

    @Test
    public void findByOrganizationPosition() {
        Optional<Principal> principal = principalService.findByOrganizationPosition(findByOrganizationPositionInit());
        assertThat(principal).isPresent();
        principal = principalService.findByOrganizationPosition("123");
        assertThat(principal).isNotPresent();
    }

    @Test
    public void findLeaderById() {
        Organization organizationLeader = new Organization().setType(OrganizationTypeEnum.POSITION);
        organizationService.save(organizationLeader);
        Organization organization = new Organization().setType(OrganizationTypeEnum.POSITION).setParentPositionId(organizationLeader.getId());
        organizationService.save(organization);
        Principal principalLeader = new Principal().setCode("1");
        Principal principal= new Principal().setCode("2");
        principalService.save(principalLeader);
        principalService.save(principal);
        principalOrganizationService.save(new PrincipalOrganization().setPrincipalId(principalLeader.getId()).setOrganizationId(organizationLeader.getId()));
        principalOrganizationService.save(new PrincipalOrganization().setPrincipalId(principal.getId()).setOrganizationId(organization.getId()));

        Optional<Principal> principalOptional = principalService.findLeaderById(principal.getId());
        assertThat(principalOptional).isPresent().get().hasFieldOrPropertyWithValue("code", principalLeader.getCode());

        principalOptional = principalService.findLeaderById(principalLeader.getId());
        assertThat(principalOptional).isNotPresent();
    }
}