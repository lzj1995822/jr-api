package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.RecordHistory;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistoryDTO;
import com.cloudkeeper.leasing.identity.dto.recordhistory.RecordHistorySearchable;
import com.cloudkeeper.leasing.identity.service.RecordHistoryService;
import com.cloudkeeper.leasing.identity.vo.RecordHistoryVO;
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
 * 活动记录历史 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordHistoryControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 活动记录历史 service */
    @MockBean
    private RecordHistoryService recordHistoryService;

    private String id = UUID.randomUUID().toString();
    private RecordHistoryDTO recordHistoryDTO = new RecordHistoryDTO();
    private RecordHistory recordHistory = (RecordHistory) recordHistoryDTO.convert(RecordHistory.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<RecordHistoryVO>> typeVO = new ParameterizedTypeReference<Result<RecordHistoryVO>>() { };
    private ParameterizedTypeReference<Result<List<RecordHistoryVO>>> typeList = new ParameterizedTypeReference<Result<List<RecordHistoryVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<RecordHistoryVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<RecordHistoryVO>>>() { };

    @Before
    public void setUp() {
        given(recordHistoryService.findOptionalById(id)).willReturn(Optional.of(recordHistory));
        doAnswer(invocationOnMock -> {
            RecordHistory recordHistory = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(recordHistory.getId())) {
                recordHistory.setId(UUID.randomUUID().toString());
            }
            return recordHistory;
        }).when(recordHistoryService).save(any(RecordHistory.class));
        List<RecordHistory> recordHistoryList = Collections.singletonList(recordHistory);
        given(recordHistoryService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(recordHistoryList);
        given(recordHistoryService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(recordHistoryList, invocationOnMock.getArgument(1), recordHistoryList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<RecordHistoryVO>> responseEntity = restTemplate.exchange("/recordHistory/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/recordHistory/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<RecordHistoryDTO> requestEntity = new HttpEntity<>(recordHistoryDTO);
        ResponseEntity<Result<RecordHistoryVO>> responseEntity = restTemplate.exchange("/recordHistory/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<RecordHistoryDTO> requestEntity = new HttpEntity<>(recordHistoryDTO);
        ResponseEntity<Result<RecordHistoryVO>> responseEntity = restTemplate.exchange("/recordHistory/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordHistoryVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/recordHistory/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/recordHistory/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<RecordHistorySearchable> requestEntity = new HttpEntity<>(new RecordHistorySearchable());
        ResponseEntity<Result<List<RecordHistoryVO>>> responseEntity = restTemplate.exchange("/recordHistory/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<RecordHistoryVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<RecordHistorySearchable> requestEntity = new HttpEntity<>(new RecordHistorySearchable());
        ResponseEntity<Result<RestPageImpl<RecordHistoryVO>>> responseEntity = restTemplate.exchange("/recordHistory/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<RecordHistoryVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}