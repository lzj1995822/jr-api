package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.domain.OrganizationRole;
import com.cloudkeeper.leasing.identity.domain.Role;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 组织角色 repository 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRoleRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 组织角色 repository */
    @Autowired
    private OrganizationRoleRepository organizationRoleRepository;

    private Organization organization;
    private List<Role> roleList = new ArrayList<>();

    @Before
    public void setUp() {
        organization = entityManager.persist(new Organization());

        Role role = entityManager.persist(new Role());
        roleList.add(role);
        OrganizationRole organizationRole = new OrganizationRole().setOrganizationId(organization.getId()).setRoleId(role.getId());
        entityManager.persist(organizationRole);

        role = entityManager.persist(new Role());
        roleList.add(role);
        organizationRole = new OrganizationRole().setOrganizationId(organization.getId()).setRoleId(role.getId());
        entityManager.persist(organizationRole);

    }

    @After
    public void tearDown() {
        organizationRoleRepository.deleteAll();
        entityManager.remove(organization);
        roleList.forEach(entityManager::remove);
    }

    @Test
    public void findAllByOrganizationId() {
        List<OrganizationRole> organizationRoleList = organizationRoleRepository.findAllByOrganizationId(organization.getId());
        assertThat(organizationRoleList).hasSize(2);
        organizationRoleList = organizationRoleRepository.findAllByOrganizationId(organization.getId() + "1");
        assertThat(organizationRoleList).isEmpty();
    }

    @Test
    public void deleteAllByOrganizationId() {
        List<OrganizationRole> organizationRoleList = organizationRoleRepository.findAllByOrganizationId(organization.getId());
        assertThat(organizationRoleList).hasSize(2);
        organizationRoleRepository.deleteAllByOrganizationId(organization.getId());
        organizationRoleList = organizationRoleRepository.findAllByOrganizationId(organization.getId() + "1");
        assertThat(organizationRoleList).isEmpty();
    }
}