package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.OrgRoom;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomDTO;
import com.cloudkeeper.leasing.identity.dto.orgroom.OrgRoomSearchable;
import com.cloudkeeper.leasing.identity.service.OrgRoomService;
import com.cloudkeeper.leasing.identity.vo.OrgRoomVO;
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
 * 功能室 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrgRoomControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 功能室 service */
    @MockBean
    private OrgRoomService orgRoomService;

    private String id = UUID.randomUUID().toString();
    private OrgRoomDTO orgRoomDTO = new OrgRoomDTO();
    private OrgRoom orgRoom = (OrgRoom) orgRoomDTO.convert(OrgRoom.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<OrgRoomVO>> typeVO = new ParameterizedTypeReference<Result<OrgRoomVO>>() { };
    private ParameterizedTypeReference<Result<List<OrgRoomVO>>> typeList = new ParameterizedTypeReference<Result<List<OrgRoomVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<OrgRoomVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<OrgRoomVO>>>() { };

    @Before
    public void setUp() {
        given(orgRoomService.findOptionalById(id)).willReturn(Optional.of(orgRoom));
        doAnswer(invocationOnMock -> {
            OrgRoom orgRoom = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(orgRoom.getId())) {
                orgRoom.setId(UUID.randomUUID().toString());
            }
            return orgRoom;
        }).when(orgRoomService).save(any(OrgRoom.class));
        List<OrgRoom> orgRoomList = Collections.singletonList(orgRoom);
        given(orgRoomService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(orgRoomList);
        given(orgRoomService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(orgRoomList, invocationOnMock.getArgument(1), orgRoomList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<OrgRoomVO>> responseEntity = restTemplate.exchange("/orgRoom/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/orgRoom/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<OrgRoomDTO> requestEntity = new HttpEntity<>(orgRoomDTO);
        ResponseEntity<Result<OrgRoomVO>> responseEntity = restTemplate.exchange("/orgRoom/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<OrgRoomDTO> requestEntity = new HttpEntity<>(orgRoomDTO);
        ResponseEntity<Result<OrgRoomVO>> responseEntity = restTemplate.exchange("/orgRoom/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<OrgRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/orgRoom/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/orgRoom/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<OrgRoomSearchable> requestEntity = new HttpEntity<>(new OrgRoomSearchable());
        ResponseEntity<Result<List<OrgRoomVO>>> responseEntity = restTemplate.exchange("/orgRoom/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<OrgRoomVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<OrgRoomSearchable> requestEntity = new HttpEntity<>(new OrgRoomSearchable());
        ResponseEntity<Result<RestPageImpl<OrgRoomVO>>> responseEntity = restTemplate.exchange("/orgRoom/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<OrgRoomVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}