package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.dto.organization.OrganizationDTO;
import com.cloudkeeper.leasing.identity.dto.organization.OrganizationSearchable;
import com.cloudkeeper.leasing.identity.enumeration.OrganizationTypeEnum;
import com.cloudkeeper.leasing.identity.service.OrganizationService;
import com.cloudkeeper.leasing.identity.vo.OrganizationVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

/**
 * 组织 controller 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 组织 service */
    @MockBean
    private OrganizationService organizationService;

    private String id = UUID.randomUUID().toString();
    private OrganizationDTO organizationDTO = new OrganizationDTO().setCode("su_c").setName("苏州分公司").setType(OrganizationTypeEnum.COMPANY).setSort(1);
    private Organization organization = (Organization) organizationDTO.convert(Organization.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<OrganizationVO>> typeVO = new ParameterizedTypeReference<Result<OrganizationVO>>() { };
    private ParameterizedTypeReference<Result<List<OrganizationVO>>> typeList = new ParameterizedTypeReference<Result<List<OrganizationVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<OrganizationVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<OrganizationVO>>>() { };

    @Before
    public void setUp() {
        given(organizationService.findOptionalById(id)).willReturn(Optional.of(organization));
        doAnswer(invocationOnMock -> {
            Organization organization = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(organization.getId())) {
                organization.setId(UUID.randomUUID().toString());
            }
            return organization;
        }).when(organizationService).save(any(Organization.class));
        List<Organization> organizationList = Collections.singletonList(organization);
        given(organizationService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(organizationList);
        given(organizationService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(organizationList, invocationOnMock.getArgument(1), organizationList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<OrganizationVO>> responseEntity = restTemplate.exchange("/organization/{id}id", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrganizationVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/organization/{id}id", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<OrganizationDTO> requestEntity = new HttpEntity<>(organizationDTO);
        ResponseEntity<Result<OrganizationVO>> responseEntity = restTemplate.exchange("/organization/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrganizationVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<OrganizationDTO> requestEntity = new HttpEntity<>(organizationDTO);
        ResponseEntity<Result<OrganizationVO>> responseEntity = restTemplate.exchange("/organization/{id}id", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrganizationVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/organization/{id}id", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/organization/{id}id", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<OrganizationSearchable> requestEntity = new HttpEntity<>(new OrganizationSearchable());
        ResponseEntity<Result<List<OrganizationVO>>> responseEntity = restTemplate.exchange("/organization/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<OrganizationVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<OrganizationSearchable> requestEntity = new HttpEntity<>(new OrganizationSearchable());
        ResponseEntity<Result<RestPageImpl<OrganizationVO>>> responseEntity = restTemplate.exchange("/organization/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<OrganizationVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

    @Test
    public void listParentPosition() {
        List<Organization> organizationList = new ArrayList<>();
        Organization organization = new Organization("1", "o1");
        organizationList.add(organization);
        organizationList.add(new Organization("2", "o2"));
        given(organizationService.listParentPosition(any())).willReturn(organizationList);
        ParameterizedTypeReference<Result<Map<String, String>>> typeMap = new ParameterizedTypeReference<Result<Map<String, String>>>() { };
        ResponseEntity<Result<Map<String, String>>> responseEntity = restTemplate.exchange("/organization/list/parentPosition/{id}", HttpMethod.GET, null, typeMap, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<Map<String, String>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent()).containsEntry(organization.getId(), organization.getName());
    }
}