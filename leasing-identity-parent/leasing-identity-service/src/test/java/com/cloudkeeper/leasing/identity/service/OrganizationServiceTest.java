package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.identity.domain.PrincipalOrganization;
import com.cloudkeeper.leasing.identity.enumeration.OrganizationTypeEnum;
import com.cloudkeeper.leasing.identity.vo.OrganizationVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrganizationServiceTest {

    /** 用户 service */
    @Autowired
    private PrincipalService principalService;

    /** 组织service*/
    @Autowired
    private OrganizationService organizationService;

    /** 用户组织 service */
    @Autowired
    private PrincipalOrganizationService principalOrganizationService;

    @Test
    public void saveTest() {
        Organization organization = new Organization();
        organization.setCode("root");
        organization.setName("新光租赁");
        organization = organizationService.save(organization);
        System.out.println(organization);
        System.out.println(organization.getId());
        assertNotNull(organization.getId());

        int companySort = 1;
        Organization organizationCompany1 = new Organization();
        organizationCompany1.setCode("group");
        organizationCompany1.setName("集团本部");
        organizationCompany1.setParentId(organization.getId());
        organizationCompany1.setType(OrganizationTypeEnum.COMPANY);
        organizationCompany1.setSort(companySort++);
        organizationService.save(organizationCompany1);

        Organization organizationCompany2 = new Organization();
        organizationCompany2.setCode("branchSZ");
        organizationCompany2.setName("苏州分公司");
        organizationCompany2.setParentId(organization.getId());
        organizationCompany2.setType(OrganizationTypeEnum.COMPANY);
        organizationCompany2.setSort(companySort++);
        organizationService.save(organizationCompany2);

        Organization organizationCompany3 = new Organization();
        organizationCompany3.setCode("branchDG");
        organizationCompany3.setName("东莞分公司");
        organizationCompany3.setParentId(organization.getId());
        organizationCompany3.setType(OrganizationTypeEnum.COMPANY);
        organizationCompany3.setSort(companySort++);
        organizationService.save(organizationCompany3);

        List<Organization> companyList = Arrays.asList(organizationCompany1, organizationCompany2, organizationCompany3);
        companyList.forEach(this::createDept);

    }

    private void createDept(@Nonnull Organization organization) {
        int deptSort = 1;
        Organization organizationDept1 = new Organization();
        organizationDept1.setCode("officeGM");
        organizationDept1.setName("总经办");
        organizationDept1.setParentId(organization.getId());
        organizationDept1.setType(OrganizationTypeEnum.DEPT);
        organizationDept1.setSort(deptSort++);
        organizationService.save(organizationDept1);

        Organization organizationPosition1 = new Organization();
        organizationPosition1.setCode("GM");
        organizationPosition1.setName("总经理");
        organizationPosition1.setParentId(organizationDept1.getId());
        organizationPosition1.setType(OrganizationTypeEnum.POSITION);
        organizationPosition1.setSort(1);
        organizationService.save(organizationPosition1);

        Organization organizationDept2 = new Organization();
        organizationDept2.setCode("BD");
        organizationDept2.setName("业务部");
        organizationDept2.setParentId(organization.getId());
        organizationDept2.setType(OrganizationTypeEnum.DEPT);
        organizationDept2.setSort(deptSort++);
        organizationService.save(organizationDept2);
        createBDChild(organizationDept2);

        Organization organizationDept3 = new Organization();
        organizationDept3.setCode("FinanceDept");
        organizationDept3.setName("财务部");
        organizationDept3.setParentId(organization.getId());
        organizationDept3.setType(OrganizationTypeEnum.DEPT);
        organizationDept3.setSort(deptSort++);
        organizationService.save(organizationDept3);
        createFinanceDeptPosition(organizationDept3);
    }

    private void createBDChild(@Nonnull Organization organization) {
        int sort = 1;
        Organization organizationDept1 = new Organization();
        organizationDept1.setCode("01");
        organizationDept1.setName("业务部1");
        organizationDept1.setParentId(organization.getId());
        organizationDept1.setType(OrganizationTypeEnum.DEPT);
        organizationDept1.setSort(sort++);
        organizationService.save(organizationDept1);

        Organization organizationDept2 = new Organization();
        organizationDept2.setCode("02");
        organizationDept2.setName("业务部2");
        organizationDept2.setParentId(organization.getId());
        organizationDept2.setType(OrganizationTypeEnum.DEPT);
        organizationDept2.setSort(sort++);
        organizationService.save(organizationDept2);

        Arrays.asList(organizationDept1, organizationDept2).forEach(this::createBDPosition);
    }

    private void createBDPosition(@Nonnull Organization organization) {
        int sort = 1;
        Organization organizationPosition1 = new Organization();
        organizationPosition1.setCode("leader");
        organizationPosition1.setName("业务主管");
        organizationPosition1.setParentId(organization.getId());
        organizationPosition1.setType(OrganizationTypeEnum.POSITION);
        organizationPosition1.setSort(sort++);
        organizationService.save(organizationPosition1);

        Organization organizationPosition2 = new Organization();
        organizationPosition2.setCode("salesman");
        organizationPosition2.setName("业务员");
        organizationPosition2.setParentId(organization.getId());
        organizationPosition2.setType(OrganizationTypeEnum.POSITION);
        organizationPosition2.setSort(sort++);
        organizationService.save(organizationPosition2);
    }

    private void createFinanceDeptPosition(@Nonnull Organization organization) {
        int sort = 1;
        Organization organizationPosition1 = new Organization();
        organizationPosition1.setCode("leader");
        organizationPosition1.setName("财务主管");
        organizationPosition1.setParentId(organization.getId());
        organizationPosition1.setType(OrganizationTypeEnum.POSITION);
        organizationPosition1.setSort(sort++);
        organizationService.save(organizationPosition1);

        Organization organizationPosition2 = new Organization();
        organizationPosition2.setCode("financial");
        organizationPosition2.setName("财务");
        organizationPosition2.setParentId(organization.getId());
        organizationPosition2.setType(OrganizationTypeEnum.POSITION);
        organizationPosition2.setSort(sort++);
        organizationService.save(organizationPosition2);
    }

    @Test
    public void findTree() {
        OrganizationVO organizationVO = organizationService.findTree();
        System.out.println(organizationVO);
    }

    @Test
    public void findAllByParentId() {
        List<Organization> organizationList = organizationService.findAllByParentId("5058d487-4497-40eb-af8b-47bc3db36359");
        assertEquals(organizationList.size(), 3);
    }

    @Test
    public void uniqueCode() {
        assertFalse(organizationService.existsCode("group", "b57dbc0e-5a41-4e47-8f07-3f57a8884636", "68476635-57d0-459a-9585-2da76727e14b"));
        assertTrue(organizationService.existsCode("branchSZ", "b57dbc0e-5a41-4e47-8f07-3f57a8884636", "68476635-57d0-459a-9585-2da76727e14b"));
        assertTrue(organizationService.existsCode("group", "b57dbc0e-5a41-4e47-8f07-3f57a8884636", ""));
        assertFalse(organizationService.existsCode("group1", "b57dbc0e-5a41-4e47-8f07-3f57a8884636", ""));
    }

    @Test
    public void findBranchById() {
        Organization organization = new Organization().setType(OrganizationTypeEnum.COMPANY);
        organization = organizationService.save(organization);
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT).setParentId(organization.getId());
        organization1 = organizationService.save(organization1);
        Organization organization2 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization2 = organizationService.save(organization2);
        Optional<Organization> organization3 = organizationService.findBranchById(organization2.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization.getId());

        organization3 = organizationService.findBranchById(organization1.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization.getId());

        organization3 = organizationService.findBranchById(organization.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization.getId());

        organization3 = organizationService.findBranchById("");
        assertThat(organization3).isNotPresent();

    }

    @Test
    public void findBranchByPrincipalId() {
        Organization organization = new Organization().setType(OrganizationTypeEnum.COMPANY);
        organization = organizationService.save(organization);
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT).setParentId(organization.getId());
        organization1 = organizationService.save(organization1);
        Organization organization2 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization2 = organizationService.save(organization2);
        Principal principal = new Principal();
        principalService.save(principal);
        PrincipalOrganization principalOrganization = new PrincipalOrganization().setPrincipalId(principal.getId()).setOrganizationId(organization2.getId());
        principalOrganizationService.save(principalOrganization);
        Optional<Organization> organization3 = organizationService.findBranchByPrincipalId(principal.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization.getId());

        organization3 = organizationService.findBranchByPrincipalId(UUID.randomUUID().toString());
        assertThat(organization3).isNotPresent();
    }

    @Test
    public void findDeptById() {
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT);
        organization1 = organizationService.save(organization1);
        Organization organization2 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization2 = organizationService.save(organization2);
        Optional<Organization> organization3 = organizationService.findDeptById(organization2.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization1.getId());

        organization3 = organizationService.findDeptById(UUID.randomUUID().toString());
        assertThat(organization3).isNotPresent();
    }

    @Test
    public void findDeptByPrincipalId() {
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT);
        organization1 = organizationService.save(organization1);
        Organization organization2 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization2 = organizationService.save(organization2);
        Principal principal = new Principal();
        principalService.save(principal);
        PrincipalOrganization principalOrganization = new PrincipalOrganization().setPrincipalId(principal.getId()).setOrganizationId(organization2.getId());
        principalOrganizationService.save(principalOrganization);
        Optional<Organization> organization3 = organizationService.findDeptByPrincipalId(principal.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization1.getId());

        organization3 = organizationService.findDeptByPrincipalId(UUID.randomUUID().toString());
        assertThat(organization3).isNotPresent();
    }

    @Test
    public void findPositionByPrincipalId() {
        Organization organization2 = new Organization().setType(OrganizationTypeEnum.POSITION);
        organization2 = organizationService.save(organization2);
        Principal principal = new Principal();
        principalService.save(principal);
        PrincipalOrganization principalOrganization = new PrincipalOrganization().setPrincipalId(principal.getId()).setOrganizationId(organization2.getId());
        principalOrganizationService.save(principalOrganization);
        Optional<Organization> organization3 = organizationService.findPositionByPrincipalId(principal.getId());
        assertThat(organization3).isPresent().get().hasFieldOrPropertyWithValue("id", organization2.getId());

        organization3 = organizationService.findPositionByPrincipalId(UUID.randomUUID().toString());
        assertThat(organization3).isNotPresent();
    }

    @Test
    public void listParentPosition() {
        Organization organization = new Organization().setType(OrganizationTypeEnum.COMPANY);
        organization = organizationService.save(organization);
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT).setParentId(organization.getId());
        organization1 = organizationService.save(organization1);
        Organization organization11 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization11 = organizationService.save(organization11);
        Organization organization12 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization12 = organizationService.save(organization12);
        List<Organization> organizationList = organizationService.listParentPosition(organization1.getId());
        assertThat(organizationList).hasSize(2);
        organizationList = organizationService.listParentPosition(organization12.getId());
        assertThat(organizationList).hasSize(1);
    }
}