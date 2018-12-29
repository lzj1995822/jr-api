package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.OrgPerson;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonDTO;
import com.cloudkeeper.leasing.identity.dto.orgperson.OrgPersonSearchable;
import com.cloudkeeper.leasing.identity.service.OrgPersonService;
import com.cloudkeeper.leasing.identity.vo.OrgPersonVO;
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
 * 组织架构人员 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrgPersonControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 组织架构人员 service */
    @MockBean
    private OrgPersonService orgPersonService;

    private String id = UUID.randomUUID().toString();
    private OrgPersonDTO orgPersonDTO = new OrgPersonDTO();
    private OrgPerson orgPerson = (OrgPerson) orgPersonDTO.convert(OrgPerson.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<OrgPersonVO>> typeVO = new ParameterizedTypeReference<Result<OrgPersonVO>>() { };
    private ParameterizedTypeReference<Result<List<OrgPersonVO>>> typeList = new ParameterizedTypeReference<Result<List<OrgPersonVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<OrgPersonVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<OrgPersonVO>>>() { };

    @Before
    public void setUp() {
        given(orgPersonService.findOptionalById(id)).willReturn(Optional.of(orgPerson));
        doAnswer(invocationOnMock -> {
            OrgPerson orgPerson = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(orgPerson.getId())) {
                orgPerson.setId(UUID.randomUUID().toString());
            }
            return orgPerson;
        }).when(orgPersonService).save(any(OrgPerson.class));
        List<OrgPerson> orgPersonList = Collections.singletonList(orgPerson);
        given(orgPersonService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(orgPersonList);
        given(orgPersonService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(orgPersonList, invocationOnMock.getArgument(1), orgPersonList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<OrgPersonVO>> responseEntity = restTemplate.exchange("/orgPerson/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgPersonVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/orgPerson/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<OrgPersonDTO> requestEntity = new HttpEntity<>(orgPersonDTO);
        ResponseEntity<Result<OrgPersonVO>> responseEntity = restTemplate.exchange("/orgPerson/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgPersonVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<OrgPersonDTO> requestEntity = new HttpEntity<>(orgPersonDTO);
        ResponseEntity<Result<OrgPersonVO>> responseEntity = restTemplate.exchange("/orgPerson/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgPersonVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/orgPerson/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/orgPerson/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<OrgPersonSearchable> requestEntity = new HttpEntity<>(new OrgPersonSearchable());
        ResponseEntity<Result<List<OrgPersonVO>>> responseEntity = restTemplate.exchange("/orgPerson/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<OrgPersonVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<OrgPersonSearchable> requestEntity = new HttpEntity<>(new OrgPersonSearchable());
        ResponseEntity<Result<RestPageImpl<OrgPersonVO>>> responseEntity = restTemplate.exchange("/orgPerson/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<OrgPersonVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}