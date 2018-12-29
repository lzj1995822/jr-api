package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Record;
import com.cloudkeeper.leasing.identity.dto.record.RecordDTO;
import com.cloudkeeper.leasing.identity.dto.record.RecordSearchable;
import com.cloudkeeper.leasing.identity.service.RecordService;
import com.cloudkeeper.leasing.identity.vo.RecordVO;
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
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 活动记录 service */
    @MockBean
    private RecordService recordService;

    private String id = UUID.randomUUID().toString();
    private RecordDTO recordDTO = new RecordDTO();
    private Record record = (Record) recordDTO.convert(Record.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<RecordVO>> typeVO = new ParameterizedTypeReference<Result<RecordVO>>() { };
    private ParameterizedTypeReference<Result<List<RecordVO>>> typeList = new ParameterizedTypeReference<Result<List<RecordVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<RecordVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<RecordVO>>>() { };

    @Before
    public void setUp() {
        given(recordService.findOptionalById(id)).willReturn(Optional.of(record));
        doAnswer(invocationOnMock -> {
            Record record = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(record.getId())) {
                record.setId(UUID.randomUUID().toString());
            }
            return record;
        }).when(recordService).save(any(Record.class));
        List<Record> recordList = Collections.singletonList(record);
        given(recordService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(recordList);
        given(recordService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(recordList, invocationOnMock.getArgument(1), recordList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<RecordVO>> responseEntity = restTemplate.exchange("/record/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/record/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<RecordDTO> requestEntity = new HttpEntity<>(recordDTO);
        ResponseEntity<Result<RecordVO>> responseEntity = restTemplate.exchange("/record/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<RecordDTO> requestEntity = new HttpEntity<>(recordDTO);
        ResponseEntity<Result<RecordVO>> responseEntity = restTemplate.exchange("/record/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/record/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/record/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<RecordSearchable> requestEntity = new HttpEntity<>(new RecordSearchable());
        ResponseEntity<Result<List<RecordVO>>> responseEntity = restTemplate.exchange("/record/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<RecordVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<RecordSearchable> requestEntity = new HttpEntity<>(new RecordSearchable());
        ResponseEntity<Result<RestPageImpl<RecordVO>>> responseEntity = restTemplate.exchange("/record/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<RecordVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}