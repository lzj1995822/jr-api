package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Point;
import com.cloudkeeper.leasing.identity.dto.point.PointDTO;
import com.cloudkeeper.leasing.identity.dto.point.PointSearchable;
import com.cloudkeeper.leasing.identity.service.PointService;
import com.cloudkeeper.leasing.identity.vo.PointVO;
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
 * 实践点 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PointControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 实践点 service */
    @MockBean
    private PointService pointService;

    private String id = UUID.randomUUID().toString();
    private PointDTO pointDTO = new PointDTO();
    private Point point = (Point) pointDTO.convert(Point.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<PointVO>> typeVO = new ParameterizedTypeReference<Result<PointVO>>() { };
    private ParameterizedTypeReference<Result<List<PointVO>>> typeList = new ParameterizedTypeReference<Result<List<PointVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<PointVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<PointVO>>>() { };

    @Before
    public void setUp() {
        given(pointService.findOptionalById(id)).willReturn(Optional.of(point));
        doAnswer(invocationOnMock -> {
            Point point = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(point.getId())) {
                point.setId(UUID.randomUUID().toString());
            }
            return point;
        }).when(pointService).save(any(Point.class));
        List<Point> pointList = Collections.singletonList(point);
        given(pointService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(pointList);
        given(pointService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(pointList, invocationOnMock.getArgument(1), pointList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<PointVO>> responseEntity = restTemplate.exchange("/point/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<PointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/point/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<PointDTO> requestEntity = new HttpEntity<>(pointDTO);
        ResponseEntity<Result<PointVO>> responseEntity = restTemplate.exchange("/point/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<PointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<PointDTO> requestEntity = new HttpEntity<>(pointDTO);
        ResponseEntity<Result<PointVO>> responseEntity = restTemplate.exchange("/point/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<PointVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/point/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/point/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<PointSearchable> requestEntity = new HttpEntity<>(new PointSearchable());
        ResponseEntity<Result<List<PointVO>>> responseEntity = restTemplate.exchange("/point/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<PointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<PointSearchable> requestEntity = new HttpEntity<>(new PointSearchable());
        ResponseEntity<Result<RestPageImpl<PointVO>>> responseEntity = restTemplate.exchange("/point/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<PointVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}