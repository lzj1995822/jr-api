package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceDTO;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceSearchable;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.vo.JrResourceVO;
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
 * 文件上传 controller 测试
 * @author hf
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JrResourceControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 文件上传 service */
    @MockBean
    private JrResourceService jrResourceService;

    private String id = UUID.randomUUID().toString();
    private JrResourceDTO jrResourceDTO = new JrResourceDTO();
    private JrResource jrResource = (JrResource) jrResourceDTO.convert(JrResource.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<JrResourceVO>> typeVO = new ParameterizedTypeReference<Result<JrResourceVO>>() { };
    private ParameterizedTypeReference<Result<List<JrResourceVO>>> typeList = new ParameterizedTypeReference<Result<List<JrResourceVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<JrResourceVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<JrResourceVO>>>() { };

    @Before
    public void setUp() {
        given(jrResourceService.findOptionalById(id)).willReturn(Optional.of(jrResource));
        doAnswer(invocationOnMock -> {
            JrResource jrResource = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(jrResource.getId())) {
                jrResource.setId(UUID.randomUUID().toString());
            }
            return jrResource;
        }).when(jrResourceService).save(any(JrResource.class));
        List<JrResource> jrResourceList = Collections.singletonList(jrResource);
        given(jrResourceService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(jrResourceList);
        given(jrResourceService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(jrResourceList, invocationOnMock.getArgument(1), jrResourceList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<JrResourceVO>> responseEntity = restTemplate.exchange("/jrResource/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<JrResourceVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/jrResource/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<JrResourceDTO> requestEntity = new HttpEntity<>(jrResourceDTO);
        ResponseEntity<Result<JrResourceVO>> responseEntity = restTemplate.exchange("/jrResource/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<JrResourceVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<JrResourceDTO> requestEntity = new HttpEntity<>(jrResourceDTO);
        ResponseEntity<Result<JrResourceVO>> responseEntity = restTemplate.exchange("/jrResource/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<JrResourceVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/jrResource/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/jrResource/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<JrResourceSearchable> requestEntity = new HttpEntity<>(new JrResourceSearchable());
        ResponseEntity<Result<List<JrResourceVO>>> responseEntity = restTemplate.exchange("/jrResource/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<JrResourceVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<JrResourceSearchable> requestEntity = new HttpEntity<>(new JrResourceSearchable());
        ResponseEntity<Result<RestPageImpl<JrResourceVO>>> responseEntity = restTemplate.exchange("/jrResource/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<JrResourceVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}