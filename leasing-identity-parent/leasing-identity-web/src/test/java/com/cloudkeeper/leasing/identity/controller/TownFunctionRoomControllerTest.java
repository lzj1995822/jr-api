package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.identity.domain.TownFunctionRoom;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomDTO;
import com.cloudkeeper.leasing.identity.dto.townfunctionroom.TownFunctionRoomSearchable;
import com.cloudkeeper.leasing.identity.service.TownFunctionRoomService;
import com.cloudkeeper.leasing.identity.vo.TownFunctionRoomVO;
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
 * 所站功能室建设 controller 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TownFunctionRoomControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 所站功能室建设 service */
    @MockBean
    private TownFunctionRoomService townFunctionRoomService;

    private String id = UUID.randomUUID().toString();
    private TownFunctionRoomDTO townFunctionRoomDTO = new TownFunctionRoomDTO();
    private TownFunctionRoom townFunctionRoom = (TownFunctionRoom) townFunctionRoomDTO.convert(TownFunctionRoom.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<TownFunctionRoomVO>> typeVO = new ParameterizedTypeReference<Result<TownFunctionRoomVO>>() { };
    private ParameterizedTypeReference<Result<List<TownFunctionRoomVO>>> typeList = new ParameterizedTypeReference<Result<List<TownFunctionRoomVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<TownFunctionRoomVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<TownFunctionRoomVO>>>() { };

    @Before
    public void setUp() {
        given(townFunctionRoomService.findOptionalById(id)).willReturn(Optional.of(townFunctionRoom));
        doAnswer(invocationOnMock -> {
            TownFunctionRoom townFunctionRoom = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(townFunctionRoom.getId())) {
                townFunctionRoom.setId(UUID.randomUUID().toString());
            }
            return townFunctionRoom;
        }).when(townFunctionRoomService).save(any(TownFunctionRoom.class));
        List<TownFunctionRoom> townFunctionRoomList = Collections.singletonList(townFunctionRoom);
        given(townFunctionRoomService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(townFunctionRoomList);
        given(townFunctionRoomService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(townFunctionRoomList, invocationOnMock.getArgument(1), townFunctionRoomList.size()));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<TownFunctionRoomVO>> responseEntity = restTemplate.exchange("/townFunctionRoom/{id}", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownFunctionRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/townFunctionRoom/{id}", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<TownFunctionRoomDTO> requestEntity = new HttpEntity<>(townFunctionRoomDTO);
        ResponseEntity<Result<TownFunctionRoomVO>> responseEntity = restTemplate.exchange("/townFunctionRoom/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownFunctionRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<TownFunctionRoomDTO> requestEntity = new HttpEntity<>(townFunctionRoomDTO);
        ResponseEntity<Result<TownFunctionRoomVO>> responseEntity = restTemplate.exchange("/townFunctionRoom/{id}", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<TownFunctionRoomVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());
        assertThat(result.getContent()).isNull();

        responseEntity = restTemplate.exchange("/townFunctionRoom/{id}", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/townFunctionRoom/{id}", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<TownFunctionRoomSearchable> requestEntity = new HttpEntity<>(new TownFunctionRoomSearchable());
        ResponseEntity<Result<List<TownFunctionRoomVO>>> responseEntity = restTemplate.exchange("/townFunctionRoom/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<TownFunctionRoomVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<TownFunctionRoomSearchable> requestEntity = new HttpEntity<>(new TownFunctionRoomSearchable());
        ResponseEntity<Result<RestPageImpl<TownFunctionRoomVO>>> responseEntity = restTemplate.exchange("/townFunctionRoom/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<TownFunctionRoomVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

}