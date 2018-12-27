package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.CenterUser;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserDTO;
import com.cloudkeeper.leasing.identity.dto.centeruser.CenterUserSearchable;
import com.cloudkeeper.leasing.identity.service.CenterUserService;
import com.cloudkeeper.leasing.identity.vo.CenterUserVO;
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
 * 分中心人员 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CenterUserControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 分中心人员 service */
    @MockBean
    private CenterUserService centerUserService;

    private String id = UUID.randomUUID().toString();
    private CenterUserDTO centerUserDTO = new CenterUserDTO();
    private CenterUser centerUser = (CenterUser) centerUserDTO.convert(CenterUser.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CenterUserVO>> typeVO = new ParameterizedTypeReference<Result<CenterUserVO>>() { };
    private ParameterizedTypeReference<Result<List<CenterUserVO>>> typeList = new ParameterizedTypeReference<Result<List<CenterUserVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CenterUserVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CenterUserVO>>>() { };

    @Before
    public void setUp() {
        given(centerUserService.findOptionalById(id)).willReturn(Optional.of(centerUser));
        doAnswer(invocationOnMock -> {
            CenterUser centerUser = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(centerUser.getId())) {
                centerUser.setId(UUID.randomUUID().toString());
            }
            return centerUser;
        }).when(centerUserService).save(any(CenterUser.class));
        List<CenterUser> centerUserList = Collections.singletonList(centerUser);
        given(centerUserService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(centerUserList);
        given(centerUserService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(centerUserList, invocationOnMock.getArgument(1), centerUserList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CenterUserVO>> responseEntity = restTemplate.exchange("/centerUser/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/centerUser/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CenterUserDTO> requestEntity = new HttpEntity<>(centerUserDTO);
        ResponseEntity<Result<CenterUserVO>> responseEntity = restTemplate.exchange("/centerUser/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CenterUserDTO> requestEntity = new HttpEntity<>(centerUserDTO);
        ResponseEntity<Result<CenterUserVO>> responseEntity = restTemplate.exchange("/centerUser/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/centerUser/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/centerUser/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CenterUserSearchable> requestEntity = new HttpEntity<>(new CenterUserSearchable());
        ResponseEntity<Result<List<CenterUserVO>>> responseEntity = restTemplate.exchange("/centerUser/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CenterUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CenterUserSearchable> requestEntity = new HttpEntity<>(new CenterUserSearchable());
        ResponseEntity<Result<RestPageImpl<CenterUserVO>>> responseEntity = restTemplate.exchange("/centerUser/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CenterUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}