package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.identity.domain.Organization;
import com.cloudkeeper.leasing.identity.domain.Principal;
import com.cloudkeeper.leasing.identity.service.OrganizationService;
import com.cloudkeeper.leasing.identity.service.PrincipalOrganizationService;
import com.cloudkeeper.leasing.identity.service.PrincipalService;
import com.cloudkeeper.leasing.identity.vo.PrincipalVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrincipalControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @MockBean
    protected PrincipalService principalService;

    @MockBean
    protected OrganizationService organizationService;

    @MockBean
    protected PrincipalOrganizationService principalOrganizationService;

    @Test
    public void findOne() {
        // mock
        String id = "1";
        Principal principal = new Principal();
        principal.setCode("aa");
        principal.setId(id);
        given(principalService.findOptionalById(id)).willReturn(Optional.of(principal));

        ParameterizedTypeReference<Result<PrincipalVO>> type = new ParameterizedTypeReference<Result<PrincipalVO>>() {};
        ResponseEntity<Result<PrincipalVO>> responseEntity = restTemplate.exchange("/principal/{id}id", HttpMethod.GET, null, type, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<PrincipalVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent().getId()).isEqualTo(principal.getId());

        responseEntity = restTemplate.exchange("/principal/{id}id", HttpMethod.GET, null, type, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.NOT_FOUND);
    }

    @Test
    public void findOneTO() {
        Principal principal = new Principal().setCode("tom").setName("汤姆");
        principal.setId(UUID.randomUUID().toString());
        Principal principal2 = new Principal().setCode("jerry").setName("杰瑞");
        principal2.setId(UUID.randomUUID().toString());
        Organization branch = new Organization().setName("集团本部");
        branch.setId(UUID.randomUUID().toString());
        Organization dept = new Organization().setName("业务部");
        dept.setId(UUID.randomUUID().toString());

        given(principalService.findOptionalById(principal.getId())).willReturn(Optional.of(principal));
        given(principalService.findOptionalById(principal2.getId())).willReturn(Optional.of(principal2));
        given(organizationService.findBranchByPrincipalId(principal.getId())).willReturn(Optional.of(branch));
        given(organizationService.findDeptByPrincipalId(principal.getId())).willReturn(Optional.of(dept));
        given(principalService.findLeaderById(principal.getId())).willReturn(Optional.of(principal2));

        ResponseEntity<PrincipalTO> responseEntity = restTemplate.exchange("/principal/to/{id}", HttpMethod.GET, null, PrincipalTO.class, principal.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        PrincipalTO result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(principal.getCode());
        assertThat(result.getName()).isEqualTo(principal.getName());
        assertThat(result.getBranch()).isEqualTo(branch.getName());
        assertThat(result.getBranchId()).isEqualTo(branch.getId());
        assertThat(result.getDeptId()).isEqualTo(dept.getId());
        assertThat(result.getDirector()).isEqualTo(principal2.getName());

        responseEntity = restTemplate.exchange("/principal/to/{id}", HttpMethod.GET, null, PrincipalTO.class, principal2.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(principal2.getCode());
        assertThat(result.getName()).isEqualTo(principal2.getName());
        assertThat(result.getBranch()).isNull();
        assertThat(result.getBranchId()).isNull();
        assertThat(result.getDeptId()).isNull();
        assertThat(result.getDirector()).isNull();

        responseEntity = restTemplate.exchange("/principal/to/{id}", HttpMethod.GET, null, PrincipalTO.class, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNull();

    }

//    @Test
//    public void login() {
//        ParameterizedTypeReference<Result<String>> type = new ParameterizedTypeReference<Result<String>>() {};
//        Principal principal = new Principal();
//        principal.setCode("admin");
//        principal.setPassword("123");
//        HttpEntity<Principal> httpEntity = new HttpEntity<>(principal);
//        ResponseEntity<Result<String>> responseEntity = restTemplate.exchange("/principal/login", HttpMethod.POST, httpEntity, type);
//        System.out.println(responseEntity.getStatusCodeValue());
//        System.out.println(responseEntity.getBody());
//    }
//
//    @Test
//    public void add() {
//        Principal principal = new Principal();
//        principal.setCode("jerry001");
//        principal.setPassword("123");
//        HttpEntity<Principal> httpEntity = new HttpEntity<>(principal, httpHeaders);
//        ParameterizedTypeReference<Result<Principal>> type = new ParameterizedTypeReference<Result<Principal>>() {};
//        ResponseEntity<Result<Principal>> responseEntity = restTemplate.exchange("/principal/add", HttpMethod.POST, httpEntity, type);
//        System.out.println(responseEntity.getStatusCodeValue());
//        System.out.println(responseEntity.getBody());
//    }
//
//    @Test
//    public void update() {
//    }
//
//    @Test
//    public void page() {
//        Principal principal = new Principal();
//        principal.setCode("jerry001");
//        principal.setPassword("123");
//        HttpEntity<Principal> httpEntity = new HttpEntity<>(principal, httpHeaders);
//        ParameterizedTypeReference<Result<RestPageImpl<Principal>>> type = new ParameterizedTypeReference<Result<RestPageImpl<Principal>>>() {};
//        URI uri = UriComponentsBuilder.fromUriString("/principal/page").queryParam("sort", "code,asc").build().toUri();
//        ResponseEntity<Result<RestPageImpl<Principal>>> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, type);
//        assert responseEntity.getStatusCodeValue() == 200;
//        System.out.println(responseEntity.getBody());
//    }
//
//    @Test
//    public void pagesp() {
//        PrincipalSearchable principalSearchable = new PrincipalSearchable();
//        HttpEntity<PrincipalSearchable> httpEntity = new HttpEntity<>(principalSearchable, httpHeaders);
//        ParameterizedTypeReference<Result<RestPageImpl<Principal>>> type = new ParameterizedTypeReference<Result<RestPageImpl<Principal>>>() {};
//        URI uri = UriComponentsBuilder.fromUriString("/principal/page/rootorganizationFullCode").queryParam("sort", "code,asc").build().toUri();
//        ResponseEntity<Result<RestPageImpl<Principal>>> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, type);
//        assert responseEntity.getStatusCodeValue() == 200;
//        System.out.println(responseEntity.getBody());
//    }

}