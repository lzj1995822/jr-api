package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.ActivityHistory;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.activityhistory.ActivityHistorySearchable;
import com.cloudkeeper.leasing.identity.service.ActivityHistoryService;
import com.cloudkeeper.leasing.identity.vo.ActivityHistoryVO;
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
 * 活动记录 controller 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityHistoryControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 活动记录 service */
    @MockBean
    private ActivityHistoryService activityHistoryService;

    private String id = UUID.randomUUID().toString();
    private ActivityHistoryDTO activityHistoryDTO = new ActivityHistoryDTO();
    private ActivityHistory activityHistory = (ActivityHistory) activityHistoryDTO.convert(ActivityHistory.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<ActivityHistoryVO>> typeVO = new ParameterizedTypeReference<Result<ActivityHistoryVO>>() { };
    private ParameterizedTypeReference<Result<List<ActivityHistoryVO>>> typeList = new ParameterizedTypeReference<Result<List<ActivityHistoryVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<ActivityHistoryVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<ActivityHistoryVO>>>() { };

    @Before
    public void setUp() {
        given(activityHistoryService.findOptionalById(id)).willReturn(Optional.of(activityHistory));
        doAnswer(invocationOnMock -> {
            ActivityHistory activityHistory = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(activityHistory.getId())) {
                activityHistory.setId(UUID.randomUUID().toString());
            }
            return activityHistory;
        }).when(activityHistoryService).save(any(ActivityHistory.class));
        List<ActivityHistory> activityHistoryList = Collections.singletonList(activityHistory);
        given(activityHistoryService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(activityHistoryList);
        given(activityHistoryService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(activityHistoryList, invocationOnMock.getArgument(1), activityHistoryList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<ActivityHistoryVO>> responseEntity = restTemplate.exchange("/activityHistory/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<ActivityHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/activityHistory/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<ActivityHistoryDTO> requestEntity = new HttpEntity<>(activityHistoryDTO);
        ResponseEntity<Result<ActivityHistoryVO>> responseEntity = restTemplate.exchange("/activityHistory/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<ActivityHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<ActivityHistoryDTO> requestEntity = new HttpEntity<>(activityHistoryDTO);
        ResponseEntity<Result<ActivityHistoryVO>> responseEntity = restTemplate.exchange("/activityHistory/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<ActivityHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/activityHistory/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/activityHistory/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<ActivityHistorySearchable> requestEntity = new HttpEntity<>(new ActivityHistorySearchable());
        ResponseEntity<Result<List<ActivityHistoryVO>>> responseEntity = restTemplate.exchange("/activityHistory/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<ActivityHistoryVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<ActivityHistorySearchable> requestEntity = new HttpEntity<>(new ActivityHistorySearchable());
        ResponseEntity<Result<RestPageImpl<ActivityHistoryVO>>> responseEntity = restTemplate.exchange("/activityHistory/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<ActivityHistoryVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}