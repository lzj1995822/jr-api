package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.TownUser;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserDTO;
import com.cloudkeeper.leasing.identity.dto.townuser.TownUserSearchable;
import com.cloudkeeper.leasing.identity.service.TownUserService;
import com.cloudkeeper.leasing.identity.vo.TownUserVO;
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
 * 所站人员 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TownUserControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 所站人员 service */
    @MockBean
    private TownUserService townUserService;

    private String id = UUID.randomUUID().toString();
    private TownUserDTO townUserDTO = new TownUserDTO();
    private TownUser townUser = (TownUser) townUserDTO.convert(TownUser.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<TownUserVO>> typeVO = new ParameterizedTypeReference<Result<TownUserVO>>() { };
    private ParameterizedTypeReference<Result<List<TownUserVO>>> typeList = new ParameterizedTypeReference<Result<List<TownUserVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<TownUserVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<TownUserVO>>>() { };

    @Before
    public void setUp() {
        given(townUserService.findOptionalById(id)).willReturn(Optional.of(townUser));
        doAnswer(invocationOnMock -> {
            TownUser townUser = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(townUser.getId())) {
                townUser.setId(UUID.randomUUID().toString());
            }
            return townUser;
        }).when(townUserService).save(any(TownUser.class));
        List<TownUser> townUserList = Collections.singletonList(townUser);
        given(townUserService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(townUserList);
        given(townUserService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(townUserList, invocationOnMock.getArgument(1), townUserList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<TownUserVO>> responseEntity = restTemplate.exchange("/townUser/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/townUser/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<TownUserDTO> requestEntity = new HttpEntity<>(townUserDTO);
        ResponseEntity<Result<TownUserVO>> responseEntity = restTemplate.exchange("/townUser/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<TownUserDTO> requestEntity = new HttpEntity<>(townUserDTO);
        ResponseEntity<Result<TownUserVO>> responseEntity = restTemplate.exchange("/townUser/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownUserVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/townUser/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/townUser/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<TownUserSearchable> requestEntity = new HttpEntity<>(new TownUserSearchable());
        ResponseEntity<Result<List<TownUserVO>>> responseEntity = restTemplate.exchange("/townUser/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<TownUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<TownUserSearchable> requestEntity = new HttpEntity<>(new TownUserSearchable());
        ResponseEntity<Result<RestPageImpl<TownUserVO>>> responseEntity = restTemplate.exchange("/townUser/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<TownUserVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}