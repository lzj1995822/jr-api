package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.OrgCenter;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterDTO;
import com.cloudkeeper.leasing.identity.dto.orgcenter.OrgCenterSearchable;
import com.cloudkeeper.leasing.identity.service.OrgCenterService;
import com.cloudkeeper.leasing.identity.vo.OrgCenterVO;
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
 * 分中心 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrgCenterControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 分中心 service */
    @MockBean
    private OrgCenterService orgCenterService;

    private String id = UUID.randomUUID().toString();
    private OrgCenterDTO orgCenterDTO = new OrgCenterDTO();
    private OrgCenter orgCenter = (OrgCenter) orgCenterDTO.convert(OrgCenter.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<OrgCenterVO>> typeVO = new ParameterizedTypeReference<Result<OrgCenterVO>>() { };
    private ParameterizedTypeReference<Result<List<OrgCenterVO>>> typeList = new ParameterizedTypeReference<Result<List<OrgCenterVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<OrgCenterVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<OrgCenterVO>>>() { };

    @Before
    public void setUp() {
        given(orgCenterService.findOptionalById(id)).willReturn(Optional.of(orgCenter));
        doAnswer(invocationOnMock -> {
            OrgCenter orgCenter = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(orgCenter.getId())) {
                orgCenter.setId(UUID.randomUUID().toString());
            }
            return orgCenter;
        }).when(orgCenterService).save(any(OrgCenter.class));
        List<OrgCenter> orgCenterList = Collections.singletonList(orgCenter);
        given(orgCenterService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(orgCenterList);
        given(orgCenterService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(orgCenterList, invocationOnMock.getArgument(1), orgCenterList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<OrgCenterVO>> responseEntity = restTemplate.exchange("/orgCenter/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgCenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/orgCenter/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<OrgCenterDTO> requestEntity = new HttpEntity<>(orgCenterDTO);
        ResponseEntity<Result<OrgCenterVO>> responseEntity = restTemplate.exchange("/orgCenter/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgCenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<OrgCenterDTO> requestEntity = new HttpEntity<>(orgCenterDTO);
        ResponseEntity<Result<OrgCenterVO>> responseEntity = restTemplate.exchange("/orgCenter/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgCenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/orgCenter/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/orgCenter/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<OrgCenterSearchable> requestEntity = new HttpEntity<>(new OrgCenterSearchable());
        ResponseEntity<Result<List<OrgCenterVO>>> responseEntity = restTemplate.exchange("/orgCenter/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<OrgCenterVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<OrgCenterSearchable> requestEntity = new HttpEntity<>(new OrgCenterSearchable());
        ResponseEntity<Result<RestPageImpl<OrgCenterVO>>> responseEntity = restTemplate.exchange("/orgCenter/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<OrgCenterVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}