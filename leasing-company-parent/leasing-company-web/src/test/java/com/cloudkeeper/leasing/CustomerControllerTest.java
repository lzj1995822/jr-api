package com.cloudkeeper.leasing;

import com.cloudkeeper.leasing.base.dto.BaseSearchable;
import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.RestPageImpl;
import com.cloudkeeper.leasing.company.domain.Customer;
import com.cloudkeeper.leasing.company.dto.customer.CustomerDTO;
import com.cloudkeeper.leasing.company.dto.customer.CustomerSearchable;
import com.cloudkeeper.leasing.company.service.CustomerService;
import com.cloudkeeper.leasing.company.vo.CustomerVO;
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
 * 角色 controller 测试
 * @author jerry
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    /** rest 请求工具 */
    @Autowired
    private TestRestTemplate restTemplate;

    /** 角色 service */
    @MockBean
    private CustomerService customerService;

    private String id = UUID.randomUUID().toString();
    private String code = "admin";
    private CustomerDTO customerDTO = new CustomerDTO();

    private Customer customer = (Customer) customerDTO.convert(Customer.class).setId(id);

    private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };
    private ParameterizedTypeReference<Result<CustomerVO>> typeVO = new ParameterizedTypeReference<Result<CustomerVO>>() { };
    private ParameterizedTypeReference<Result<List<CustomerVO>>> typeList = new ParameterizedTypeReference<Result<List<CustomerVO>>>() { };
    private ParameterizedTypeReference<Result<RestPageImpl<CustomerVO>>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<CustomerVO>>>() { };

    @Before
    public void setUp() {
        given(customerService.findOptionalById(id)).willReturn(Optional.of(customer));
        doAnswer(invocationOnMock -> {
            Customer customer = invocationOnMock.getArgument(0);
            if (!StringUtils.hasText(customer.getId())) {
                customer.setId(UUID.randomUUID().toString());
            }
            return customer;
        }).when(customerService).save(any(Customer.class));
        List<Customer> customerList = Collections.singletonList(customer);
        given(customerService.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(customerList);
        given(customerService.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(customerList, invocationOnMock.getArgument(1), customerList.size()));

    }

    @After
    public void tearDown() {
    }

    @Test
    public void findOne() {
        ResponseEntity<Result<CustomerVO>> responseEntity = restTemplate.exchange("/customer/{id}id", HttpMethod.GET, null, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CustomerVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();

        responseEntity = restTemplate.exchange("/customer/{id}id", HttpMethod.GET, null, typeVO, id + "1");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNull();
    }

    @Test
    public void add() {
        HttpEntity<CustomerDTO> requestEntity = new HttpEntity<>(customerDTO);
        ResponseEntity<Result<CustomerVO>> responseEntity = restTemplate.exchange("/customer/", HttpMethod.POST, requestEntity, typeVO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CustomerVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getId()).isNotNull();
    }

    @Test
    public void update() {
        HttpEntity<CustomerDTO> requestEntity = new HttpEntity<>(customerDTO);
        ResponseEntity<Result<CustomerVO>> responseEntity = restTemplate.exchange("/customer/{id}id", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<CustomerVO> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());

        requestEntity = new HttpEntity<>(customerDTO);
        responseEntity = restTemplate.exchange("/customer/{id}id", HttpMethod.PUT, requestEntity, typeVO, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
    }

    @Test
    public void delete() {
        ResponseEntity<Result> responseEntity = restTemplate.exchange("/customer/{id}id", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);
    }

    @Test
    public void list() {
        HttpEntity<CustomerSearchable> requestEntity = new HttpEntity<>(new CustomerSearchable());
        ResponseEntity<Result<List<CustomerVO>>> responseEntity = restTemplate.exchange("/customer/list", HttpMethod.POST, requestEntity, typeList);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<List<CustomerVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isNotEmpty();
    }

    @Test
    public void page() {
        HttpEntity<CustomerSearchable> requestEntity = new HttpEntity<>(new CustomerSearchable());
        ResponseEntity<Result<RestPageImpl<CustomerVO>>> responseEntity = restTemplate.exchange("/customer/page", HttpMethod.POST, requestEntity, typePage);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<RestPageImpl<CustomerVO>> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent().getContent()).isNotEmpty();
    }

    @Test
    public void existsCode() {
        ParameterizedTypeReference<Result<Boolean>> typeBoolean = new ParameterizedTypeReference<Result<Boolean>>() { };

        ResponseEntity<Result<Boolean>> responseEntity = restTemplate.exchange("/customer/exists/{code}code/id", HttpMethod.GET, null, typeBoolean, code);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        Result<Boolean> result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isTrue();

        responseEntity = restTemplate.exchange("/customer/exists/{code}code/{id}id", HttpMethod.GET, null, typeBoolean, code, id);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        result = responseEntity.getBody();
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());
        assertThat(result.getContent()).isNotNull();
        assertThat(result.getContent()).isFalse();
    }
}