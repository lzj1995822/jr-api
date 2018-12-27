package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigDTO;
import com.cloudkeeper.leasing.identity.dto.codeconfig.CodeConfigSearchable;
import com.cloudkeeper.leasing.identity.service.CodeConfigService;
import com.cloudkeeper.leasing.identity.vo.CodeConfigVO;
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
 * 编码配置 controller 测试
 * @author asher
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodeConfigControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 编码配置 service */
    @MockBean
    private CodeConfigService codeConfigService;

    private String id = UUID.randomUUID().toString();
    private CodeConfigDTO codeConfigDTO = new CodeConfigDTO();
    private CodeConfig codeConfig = (CodeConfig) codeConfigDTO.convert(CodeConfig.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CodeConfigVO>> typeVO = new ParameterizedTypeReference<Result<CodeConfigVO>>() { };
    private ParameterizedTypeReference<Result<List<CodeConfigVO>>> typeList = new ParameterizedTypeReference<Result<List<CodeConfigVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CodeConfigVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CodeConfigVO>>>() { };

    @Before
    public void setUp() {
        given(codeConfigService.findOptionalById(id)).willReturn(Optional.of(codeConfig));
        doAnswer(invocationOnMock -> {
            CodeConfig codeConfig = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(codeConfig.getId())) {
                codeConfig.setId(UUID.randomUUID().toString());
            }
            return codeConfig;
        }).when(codeConfigService).save(any(CodeConfig.class));
        List<CodeConfig> codeConfigList = Collections.singletonList(codeConfig);
        given(codeConfigService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(codeConfigList);
        given(codeConfigService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(codeConfigList, invocationOnMock.getArgument(1), codeConfigList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CodeConfigVO>> responseEntity = restTemplate.exchange("/codeConfig/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeConfigVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/codeConfig/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CodeConfigDTO> requestEntity = new HttpEntity<>(codeConfigDTO);
        ResponseEntity<Result<CodeConfigVO>> responseEntity = restTemplate.exchange("/codeConfig/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeConfigVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CodeConfigDTO> requestEntity = new HttpEntity<>(codeConfigDTO);
        ResponseEntity<Result<CodeConfigVO>> responseEntity = restTemplate.exchange("/codeConfig/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CodeConfigVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/codeConfig/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/codeConfig/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CodeConfigSearchable> requestEntity = new HttpEntity<>(new CodeConfigSearchable());
        ResponseEntity<Result<List<CodeConfigVO>>> responseEntity = restTemplate.exchange("/codeConfig/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CodeConfigVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CodeConfigSearchable> requestEntity = new HttpEntity<>(new CodeConfigSearchable());
        ResponseEntity<Result<RestPageImpl<CodeConfigVO>>> responseEntity = restTemplate.exchange("/codeConfig/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CodeConfigVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}