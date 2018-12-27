package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.FunctionRoomUser;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserDTO;
import com.cloudkeeper.leasing.identity.dto.functionroomuser.FunctionRoomUserSearchable;
import com.cloudkeeper.leasing.identity.service.FunctionRoomUserService;
import com.cloudkeeper.leasing.identity.vo.FunctionRoomUserVO;
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
 * 功能室人员 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionRoomUserControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 功能室人员 service */
    @MockBean
    private FunctionRoomUserService functionRoomUserService;

    private String id = UUID.randomUUID().toString();
    private FunctionRoomUserDTO functionRoomUserDTO = new FunctionRoomUserDTO();
    private FunctionRoomUser functionRoomUser = (FunctionRoomUser) functionRoomUserDTO.convert(FunctionRoomUser.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<FunctionRoomUserVO>> typeVO = new ParameterizedTypeReference<Result<FunctionRoomUserVO>>() { };
    private ParameterizedTypeReference<Result<List<FunctionRoomUserVO>>> typeList = new ParameterizedTypeReference<Result<List<FunctionRoomUserVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<FunctionRoomUserVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<FunctionRoomUserVO>>>() { };

    @Before
    public void setUp() {
        given(functionRoomUserService.findOptionalById(id)).willReturn(Optional.of(functionRoomUser));
        doAnswer(invocationOnMock -> {
            FunctionRoomUser functionRoomUser = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(functionRoomUser.getId())) {
                functionRoomUser.setId(UUID.randomUUID().toString());
            }
            return functionRoomUser;
        }).when(functionRoomUserService).save(any(FunctionRoomUser.class));
        List<FunctionRoomUser> functionRoomUserList = Collections.singletonList(functionRoomUser);
        given(functionRoomUserService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(functionRoomUserList);
        given(functionRoomUserService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(functionRoomUserList, invocationOnMock.getArgument(1), functionRoomUserList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<FunctionRoomUserVO>> responseEntity = restTemplate.exchange("/functionRoomUser/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<FunctionRoomUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/functionRoomUser/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<FunctionRoomUserDTO> requestEntity = new HttpEntity<>(functionRoomUserDTO);
        ResponseEntity<Result<FunctionRoomUserVO>> responseEntity = restTemplate.exchange("/functionRoomUser/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<FunctionRoomUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<FunctionRoomUserDTO> requestEntity = new HttpEntity<>(functionRoomUserDTO);
        ResponseEntity<Result<FunctionRoomUserVO>> responseEntity = restTemplate.exchange("/functionRoomUser/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<FunctionRoomUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/functionRoomUser/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/functionRoomUser/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<FunctionRoomUserSearchable> requestEntity = new HttpEntity<>(new FunctionRoomUserSearchable());
        ResponseEntity<Result<List<FunctionRoomUserVO>>> responseEntity = restTemplate.exchange("/functionRoomUser/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<FunctionRoomUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<FunctionRoomUserSearchable> requestEntity = new HttpEntity<>(new FunctionRoomUserSearchable());
        ResponseEntity<Result<RestPageImpl<FunctionRoomUserVO>>> responseEntity = restTemplate.exchange("/functionRoomUser/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<FunctionRoomUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}