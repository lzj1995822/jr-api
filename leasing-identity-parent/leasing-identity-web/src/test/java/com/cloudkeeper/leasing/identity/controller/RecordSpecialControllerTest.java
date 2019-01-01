package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.RecordSpecial;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialDTO;
import com.cloudkeeper.leasing.identity.dto.recordspecial.RecordSpecialSearchable;
import com.cloudkeeper.leasing.identity.service.RecordSpecialService;
import com.cloudkeeper.leasing.identity.vo.RecordSpecialVO;
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
 * 特殊活动 controller 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordSpecialControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 特殊活动 service */
    @MockBean
    private RecordSpecialService recordSpecialService;

    private String id = UUID.randomUUID().toString();
    private RecordSpecialDTO recordSpecialDTO = new RecordSpecialDTO();
    private RecordSpecial recordSpecial = (RecordSpecial) recordSpecialDTO.convert(RecordSpecial.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<RecordSpecialVO>> typeVO = new ParameterizedTypeReference<Result<RecordSpecialVO>>() { };
    private ParameterizedTypeReference<Result<List<RecordSpecialVO>>> typeList = new ParameterizedTypeReference<Result<List<RecordSpecialVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<RecordSpecialVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<RecordSpecialVO>>>() { };

    @Before
    public void setUp() {
        given(recordSpecialService.findOptionalById(id)).willReturn(Optional.of(recordSpecial));
        doAnswer(invocationOnMock -> {
            RecordSpecial recordSpecial = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(recordSpecial.getId())) {
                recordSpecial.setId(UUID.randomUUID().toString());
            }
            return recordSpecial;
        }).when(recordSpecialService).save(any(RecordSpecial.class));
        List<RecordSpecial> recordSpecialList = Collections.singletonList(recordSpecial);
        given(recordSpecialService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(recordSpecialList);
        given(recordSpecialService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(recordSpecialList, invocationOnMock.getArgument(1), recordSpecialList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<RecordSpecialVO>> responseEntity = restTemplate.exchange("/recordSpecial/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordSpecialVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/recordSpecial/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<RecordSpecialDTO> requestEntity = new HttpEntity<>(recordSpecialDTO);
        ResponseEntity<Result<RecordSpecialVO>> responseEntity = restTemplate.exchange("/recordSpecial/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordSpecialVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<RecordSpecialDTO> requestEntity = new HttpEntity<>(recordSpecialDTO);
        ResponseEntity<Result<RecordSpecialVO>> responseEntity = restTemplate.exchange("/recordSpecial/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RecordSpecialVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/recordSpecial/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/recordSpecial/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<RecordSpecialSearchable> requestEntity = new HttpEntity<>(new RecordSpecialSearchable());
        ResponseEntity<Result<List<RecordSpecialVO>>> responseEntity = restTemplate.exchange("/recordSpecial/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<RecordSpecialVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<RecordSpecialSearchable> requestEntity = new HttpEntity<>(new RecordSpecialSearchable());
        ResponseEntity<Result<RestPageImpl<RecordSpecialVO>>> responseEntity = restTemplate.exchange("/recordSpecial/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<RecordSpecialVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}