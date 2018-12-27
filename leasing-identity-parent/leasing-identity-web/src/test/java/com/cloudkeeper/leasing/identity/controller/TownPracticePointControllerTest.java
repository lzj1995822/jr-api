package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.TownPracticePoint;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointDTO;
import com.cloudkeeper.leasing.identity.dto.townpracticepoint.TownPracticePointSearchable;
import com.cloudkeeper.leasing.identity.service.TownPracticePointService;
import com.cloudkeeper.leasing.identity.vo.TownPracticePointVO;
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
 * 镇文明实践点 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TownPracticePointControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 镇文明实践点 service */
    @MockBean
    private TownPracticePointService townPracticePointService;

    private String id = UUID.randomUUID().toString();
    private TownPracticePointDTO townPracticePointDTO = new TownPracticePointDTO();
    private TownPracticePoint townPracticePoint = (TownPracticePoint) townPracticePointDTO.convert(TownPracticePoint.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<TownPracticePointVO>> typeVO = new ParameterizedTypeReference<Result<TownPracticePointVO>>() { };
    private ParameterizedTypeReference<Result<List<TownPracticePointVO>>> typeList = new ParameterizedTypeReference<Result<List<TownPracticePointVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<TownPracticePointVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<TownPracticePointVO>>>() { };

    @Before
    public void setUp() {
        given(townPracticePointService.findOptionalById(id)).willReturn(Optional.of(townPracticePoint));
        doAnswer(invocationOnMock -> {
            TownPracticePoint townPracticePoint = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(townPracticePoint.getId())) {
                townPracticePoint.setId(UUID.randomUUID().toString());
            }
            return townPracticePoint;
        }).when(townPracticePointService).save(any(TownPracticePoint.class));
        List<TownPracticePoint> townPracticePointList = Collections.singletonList(townPracticePoint);
        given(townPracticePointService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(townPracticePointList);
        given(townPracticePointService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(townPracticePointList, invocationOnMock.getArgument(1), townPracticePointList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<TownPracticePointVO>> responseEntity = restTemplate.exchange("/townPracticePoint/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/townPracticePoint/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<TownPracticePointDTO> requestEntity = new HttpEntity<>(townPracticePointDTO);
        ResponseEntity<Result<TownPracticePointVO>> responseEntity = restTemplate.exchange("/townPracticePoint/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<TownPracticePointDTO> requestEntity = new HttpEntity<>(townPracticePointDTO);
        ResponseEntity<Result<TownPracticePointVO>> responseEntity = restTemplate.exchange("/townPracticePoint/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownPracticePointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/townPracticePoint/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/townPracticePoint/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<TownPracticePointSearchable> requestEntity = new HttpEntity<>(new TownPracticePointSearchable());
        ResponseEntity<Result<List<TownPracticePointVO>>> responseEntity = restTemplate.exchange("/townPracticePoint/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<TownPracticePointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<TownPracticePointSearchable> requestEntity = new HttpEntity<>(new TownPracticePointSearchable());
        ResponseEntity<Result<RestPageImpl<TownPracticePointVO>>> responseEntity = restTemplate.exchange("/townPracticePoint/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<TownPracticePointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}