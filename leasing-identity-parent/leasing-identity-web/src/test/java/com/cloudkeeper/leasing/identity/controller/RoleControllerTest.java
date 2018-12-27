package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Role;
import com.cloudkeeper.leasing.identity.dto.role.RoleDTO;
import com.cloudkeeper.leasing.identity.dto.role.RoleSearchable;
import com.cloudkeeper.leasing.identity.service.RoleService;
import com.cloudkeeper.leasing.identity.vo.RoleVO;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

/**
 * 角色 controller 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 角色 service */
    @MockBean
    private RoleService roleService;

    private String id = UUID.randomUUID().toString();
    private String code = "admin";
    private RoleDTO roleDTO = new RoleDTO().setCode(code).setName("超级管理员");
    private Role role = (Role) roleDTO.convert(Role.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<RoleVO>> typeVO = new ParameterizedTypeReference<Result<RoleVO>>() { };
    private ParameterizedTypeReference<Result<List<RoleVO>>> typeList = new ParameterizedTypeReference<Result<List<RoleVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<RoleVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<RoleVO>>>() { };

    @Before
    public void setUp() {
        given(roleService.findOptionalById(id)).willReturn(Optional.of(role));
        doAnswer(invocationOnMock -> {
            Role role = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(role.getId())) {
                role.setId(UUID.randomUUID().toString());
            }
            return role;
        }).when(roleService).save(any(Role.class));
        List<Role> roleList = Collections.singletonList(role);
        given(roleService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(roleList);
        given(roleService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(roleList, invocationOnMock.getArgument(1), roleList.size()));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<RoleVO>> responseEntity = restTemplate.exchange("/role/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RoleVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/role/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<RoleDTO> requestEntity = new HttpEntity<>(roleDTO);
        ResponseEntity<Result<RoleVO>> responseEntity = restTemplate.exchange("/role/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RoleVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<RoleDTO> requestEntity = new HttpEntity<>(roleDTO);
        ResponseEntity<Result<RoleVO>> responseEntity = restTemplate.exchange("/role/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RoleVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());

        roleDTO.setCode(roleDTO.getCode() + "1");
        requestEntity = new HttpEntity<>(roleDTO);
        responseEntity = restTemplate.exchange("/role/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getCode()).isEqualTo(roleDTO.getCode());
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/role/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<RoleSearchable> requestEntity = new HttpEntity<>(new RoleSearchable());
        ResponseEntity<Result<List<RoleVO>>> responseEntity = restTemplate.exchange("/role/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<RoleVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<RoleSearchable> requestEntity = new HttpEntity<>(new RoleSearchable());
        ResponseEntity<Result<RestPageImpl<RoleVO>>> responseEntity = restTemplate.exchange("/role/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<RoleVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

    @Test
    public void existsCode() {
        given(roleService.existsCode(code, id)).willReturn(false);
        given(roleService.existsCode(code, "")).willReturn(true);
        ParameterizedTypeReference<Result<Boolean>> typeBoolean = new ParameterizedTypeReference<Result<Boolean>>() { };

        ResponseEntity<Result<Boolean>> responseEntity = restTemplate.exchange("/role/exists/{code}code/id", HttpMethod.GET, null, typeBoolean, code);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<Boolean> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isTrue();

        responseEntity = restTemplate.exchange("/role/exists/{code}code/{id}id", HttpMethod.GET, null, typeBoolean, code, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isFalse();
    }
}