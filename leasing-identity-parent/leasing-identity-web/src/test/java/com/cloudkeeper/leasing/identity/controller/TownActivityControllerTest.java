package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.TownActivity;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivityDTO;
import com.cloudkeeper.leasing.identity.dto.townactivity.TownActivitySearchable;
import com.cloudkeeper.leasing.identity.service.TownActivityService;
import com.cloudkeeper.leasing.identity.vo.TownActivityVO;
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
 * 镇活动 controller 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TownActivityControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 镇活动 service */
    @MockBean
    private TownActivityService townActivityService;

    private String id = UUID.randomUUID().toString();
    private TownActivityDTO townActivityDTO = new TownActivityDTO();
    private TownActivity townActivity = (TownActivity) townActivityDTO.convert(TownActivity.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<TownActivityVO>> typeVO = new ParameterizedTypeReference<Result<TownActivityVO>>() { };
    private ParameterizedTypeReference<Result<List<TownActivityVO>>> typeList = new ParameterizedTypeReference<Result<List<TownActivityVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<TownActivityVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<TownActivityVO>>>() { };

    @Before
    public void setUp() {
        given(townActivityService.findOptionalById(id)).willReturn(Optional.of(townActivity));
        doAnswer(invocationOnMock -> {
            TownActivity townActivity = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(townActivity.getId())) {
                townActivity.setId(UUID.randomUUID().toString());
            }
            return townActivity;
        }).when(townActivityService).save(any(TownActivity.class));
        List<TownActivity> townActivityList = Collections.singletonList(townActivity);
        given(townActivityService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(townActivityList);
        given(townActivityService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(townActivityList, invocationOnMock.getArgument(1), townActivityList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<TownActivityVO>> responseEntity = restTemplate.exchange("/townActivity/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownActivityVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/townActivity/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<TownActivityDTO> requestEntity = new HttpEntity<>(townActivityDTO);
        ResponseEntity<Result<TownActivityVO>> responseEntity = restTemplate.exchange("/townActivity/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownActivityVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<TownActivityDTO> requestEntity = new HttpEntity<>(townActivityDTO);
        ResponseEntity<Result<TownActivityVO>> responseEntity = restTemplate.exchange("/townActivity/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownActivityVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/townActivity/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/townActivity/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<TownActivitySearchable> requestEntity = new HttpEntity<>(new TownActivitySearchable());
        ResponseEntity<Result<List<TownActivityVO>>> responseEntity = restTemplate.exchange("/townActivity/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<TownActivityVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<TownActivitySearchable> requestEntity = new HttpEntity<>(new TownActivitySearchable());
        ResponseEntity<Result<RestPageImpl<TownActivityVO>>> responseEntity = restTemplate.exchange("/townActivity/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<TownActivityVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}