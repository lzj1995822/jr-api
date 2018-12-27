package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordDTO;
import com.cloudkeeper.leasing.identity.dto.coderecord.CodeRecordSearchable;
import com.cloudkeeper.leasing.identity.service.CodeRecordService;
import com.cloudkeeper.leasing.identity.vo.CodeRecordVO;
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
 * 编码生成记录 controller 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeRecordControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 编码生成记录 service */
    @MockBean
    private CodeRecordService codeRecordService;

    private String id = UUID.randomUUID().toString();
    private CodeRecordDTO codeRecordDTO = new CodeRecordDTO();
    private CodeRecord codeRecord = (CodeRecord) codeRecordDTO.convert(CodeRecord.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CodeRecordVO>> typeVO = new ParameterizedTypeReference<Result<CodeRecordVO>>() { };
    private ParameterizedTypeReference<Result<List<CodeRecordVO>>> typeList = new ParameterizedTypeReference<Result<List<CodeRecordVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CodeRecordVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CodeRecordVO>>>() { };

    @Before
    public void setUp() {
        given(codeRecordService.findOptionalById(id)).willReturn(Optional.of(codeRecord));
        doAnswer(invocationOnMock -> {
            CodeRecord codeRecord = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(codeRecord.getId())) {
                codeRecord.setId(UUID.randomUUID().toString());
            }
            return codeRecord;
        }).when(codeRecordService).save(any(CodeRecord.class));
        List<CodeRecord> codeRecordList = Collections.singletonList(codeRecord);
        given(codeRecordService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(codeRecordList);
        given(codeRecordService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(codeRecordList, invocationOnMock.getArgument(1), codeRecordList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CodeRecordVO>> responseEntity = restTemplate.exchange("/codeRecord/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeRecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/codeRecord/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CodeRecordDTO> requestEntity = new HttpEntity<>(codeRecordDTO);
        ResponseEntity<Result<CodeRecordVO>> responseEntity = restTemplate.exchange("/codeRecord/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeRecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CodeRecordDTO> requestEntity = new HttpEntity<>(codeRecordDTO);
        ResponseEntity<Result<CodeRecordVO>> responseEntity = restTemplate.exchange("/codeRecord/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeRecordVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/codeRecord/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/codeRecord/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CodeRecordSearchable> requestEntity = new HttpEntity<>(new CodeRecordSearchable());
        ResponseEntity<Result<List<CodeRecordVO>>> responseEntity = restTemplate.exchange("/codeRecord/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CodeRecordVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CodeRecordSearchable> requestEntity = new HttpEntity<>(new CodeRecordSearchable());
        ResponseEntity<Result<RestPageImpl<CodeRecordVO>>> responseEntity = restTemplate.exchange("/codeRecord/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CodeRecordVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}