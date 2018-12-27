package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.enumeration.OrganizationTypeEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrganizationRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 组织repository */
    @Autowired
    private OrganizationRepository organizationRepository;

    private static final String CODE_A = "a";
    private static final String CODE_AA = "aa";
    private static final String CODE_AB = "ab";
    private static final String CODE_AC = "ac";
    private Organization organizationA;
    private Organization organizationAB;

    @Before
    public void setUp() {
        organizationA = new Organization().setCode(CODE_A);
        organizationA = entityManager.persist(organizationA);
        organizationAB = new Organization().setCode(CODE_AB).setParentId(organizationA.getId()).setSort(2);
        organizationAB = entityManager.persist(organizationAB);
        Organization organizationAC = new Organization().setCode(CODE_AC).setParentId(organizationA.getId()).setSort(1);
        organizationAC = entityManager.persist(organizationAC);
    }

    @After
    public void tearDown() {
        organizationRepository.deleteAll();
    }

    @Test
    public void findByCode() {
        Organization organization = organizationRepository.findByCode(CODE_A);
        assertThat(organization.getId()).isEqualTo(organizationA.getId());
        organization = organizationRepository.findByCode(CODE_A + "1");
        assertThat(organization).isNull();
    }

    @Test
    public void findAllByParentIdOrderBySortAsc() {
        List<Organization> organizationList = organizationRepository.findAllByParentIdOrderBySortAsc(organizationA.getId());
        assertThat(organizationList).hasSize(2);
        assertThat(organizationList).first().hasFieldOrPropertyWithValue("sort", 1);
        assertThat(organizationList).last().hasFieldOrPropertyWithValue("sort", 2);
    }

    @Test
    public void existsByParentIdAndCodeAndIdNot() {
        boolean exists = organizationRepository.existsByParentIdAndCodeAndIdNot(organizationAB.getParentId(), CODE_AB, organizationAB.getId());
        assertThat(exists).isFalse();
        exists = organizationRepository.existsByParentIdAndCodeAndIdNot(organizationAB.getParentId(), CODE_AC, organizationAB.getId());
        assertThat(exists).isTrue();
    }

    @Test
    public void existsByParentIdAndCode() {
        boolean exists = organizationRepository.existsByParentIdAndCode(organizationA.getId(), CODE_AA);
        assertThat(exists).isFalse();
        exists = organizationRepository.existsByParentIdAndCode(organizationA.getId(), CODE_AC);
        assertThat(exists).isTrue();
    }

    @Test
    public void findAllByParentIdAndTypeAndIdNot() {
        Organization organization = new Organization().setType(OrganizationTypeEnum.COMPANY);
        organization = entityManager.persist(organization);
        Organization organization1 = new Organization().setType(OrganizationTypeEnum.DEPT).setParentId(organization.getId());
        organization1 = entityManager.persist(organization1);
        Organization organization11 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization11 = entityManager.persist(organization11);
        Organization organization12 = new Organization().setType(OrganizationTypeEnum.POSITION).setParentId(organization1.getId());
        organization12 = entityManager.persist(organization12);
        List<Organization> organizationList = organizationRepository.findAllByParentIdAndTypeAndIdNot(organization1.getId(), OrganizationTypeEnum.POSITION, organization12.getId());
        assertThat(organizationList).hasSize(1);
        organizationList = organizationRepository.findAllByParentIdAndTypeAndIdNot(organization1.getId(), OrganizationTypeEnum.POSITION, "");
        assertThat(organizationList).hasSize(2);

    }
}