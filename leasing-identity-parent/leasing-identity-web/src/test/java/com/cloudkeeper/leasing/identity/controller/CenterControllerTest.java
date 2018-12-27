package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.Center;
import com.cloudkeeper.leasing.identity.dto.center.CenterDTO;
import com.cloudkeeper.leasing.identity.dto.center.CenterSearchable;
import com.cloudkeeper.leasing.identity.service.CenterService;
import com.cloudkeeper.leasing.identity.vo.CenterVO;
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
public class CenterControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 分中心 service */
    @MockBean
    private CenterService centerService;

    private String id = UUID.randomUUID().toString();
    private CenterDTO centerDTO = new CenterDTO();
    private Center center = (Center) centerDTO.convert(Center.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CenterVO>> typeVO = new ParameterizedTypeReference<Result<CenterVO>>() { };
    private ParameterizedTypeReference<Result<List<CenterVO>>> typeList = new ParameterizedTypeReference<Result<List<CenterVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CenterVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CenterVO>>>() { };

    @Before
    public void setUp() {
        given(centerService.findOptionalById(id)).willReturn(Optional.of(center));
        doAnswer(invocationOnMock -> {
            Center center = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(center.getId())) {
                center.setId(UUID.randomUUID().toString());
            }
            return center;
        }).when(centerService).save(any(Center.class));
        List<Center> centerList = Collections.singletonList(center);
        given(centerService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(centerList);
        given(centerService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(centerList, invocationOnMock.getArgument(1), centerList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CenterVO>> responseEntity = restTemplate.exchange("/center/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/center/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CenterDTO> requestEntity = new HttpEntity<>(centerDTO);
        ResponseEntity<Result<CenterVO>> responseEntity = restTemplate.exchange("/center/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CenterDTO> requestEntity = new HttpEntity<>(centerDTO);
        ResponseEntity<Result<CenterVO>> responseEntity = restTemplate.exchange("/center/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CenterVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/center/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/center/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CenterSearchable> requestEntity = new HttpEntity<>(new CenterSearchable());
        ResponseEntity<Result<List<CenterVO>>> responseEntity = restTemplate.exchange("/center/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CenterVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CenterSearchable> requestEntity = new HttpEntity<>(new CenterSearchable());
        ResponseEntity<Result<RestPageImpl<CenterVO>>> responseEntity = restTemplate.exchange("/center/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CenterVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}