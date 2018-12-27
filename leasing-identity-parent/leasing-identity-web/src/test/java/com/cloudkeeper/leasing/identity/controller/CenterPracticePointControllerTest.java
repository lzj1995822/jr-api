package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.CenterPracticePoint;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.centerpracticepoint.CenterPracticePointSearchable;
import com.cloudkeeper.leasing.identity.service.CenterPracticePointService;
import com.cloudkeeper.leasing.identity.vo.CenterPracticePointVO;
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
 * 分中心文明实践点 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CenterPracticePointControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 分中心文明实践点 service */
    @MockBean
    private CenterPracticePointService centerPracticePointService;

    private String id = UUID.randomUUID().toString();
    private CenterPracticePointDTO centerPracticePointDTO = new CenterPracticePointDTO();
    private CenterPracticePoint centerPracticePoint = (CenterPracticePoint) centerPracticePointDTO.convert(CenterPracticePoint.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CenterPracticePointVO>> typeVO = new ParameterizedTypeReference<Result<CenterPracticePointVO>>() { };
    private ParameterizedTypeReference<Result<List<CenterPracticePointVO>>> typeList = new ParameterizedTypeReference<Result<List<CenterPracticePointVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CenterPracticePointVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CenterPracticePointVO>>>() { };

    @Before
    public void setUp() {
        given(centerPracticePointService.findOptionalById(id)).willReturn(Optional.of(centerPracticePoint));
        doAnswer(invocationOnMock -> {
            CenterPracticePoint centerPracticePoint = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(centerPracticePoint.getId())) {
                centerPracticePoint.setId(UUID.randomUUID().toString());
            }
            return centerPracticePoint;
        }).when(centerPracticePointService).save(any(CenterPracticePoint.class));
        List<CenterPracticePoint> centerPracticePointList = Collections.singletonList(centerPracticePoint);
        given(centerPracticePointService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(centerPracticePointList);
        given(centerPracticePointService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(centerPracticePointList, invocationOnMock.getArgument(1), centerPracticePointList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CenterPracticePointVO>> responseEntity = restTemplate.exchange("/centerPracticePoint/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/centerPracticePoint/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CenterPracticePointDTO> requestEntity = new HttpEntity<>(centerPracticePointDTO);
        ResponseEntity<Result<CenterPracticePointVO>> responseEntity = restTemplate.exchange("/centerPracticePoint/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CenterPracticePointDTO> requestEntity = new HttpEntity<>(centerPracticePointDTO);
        ResponseEntity<Result<CenterPracticePointVO>> responseEntity = restTemplate.exchange("/centerPracticePoint/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/centerPracticePoint/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/centerPracticePoint/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CenterPracticePointSearchable> requestEntity = new HttpEntity<>(new CenterPracticePointSearchable());
        ResponseEntity<Result<List<CenterPracticePointVO>>> responseEntity = restTemplate.exchange("/centerPracticePoint/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CenterPracticePointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CenterPracticePointSearchable> requestEntity = new HttpEntity<>(new CenterPracticePointSearchable());
        ResponseEntity<Result<RestPageImpl<CenterPracticePointVO>>> responseEntity = restTemplate.exchange("/centerPracticePoint/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CenterPracticePointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}