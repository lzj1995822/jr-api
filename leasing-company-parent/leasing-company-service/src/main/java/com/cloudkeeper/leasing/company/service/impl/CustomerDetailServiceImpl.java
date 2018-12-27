package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.CustomerDetail;
import com.cloudkeeper.leasing.company.dto.customerdetail.CustomerDetailDTO;
import com.cloudkeeper.leasing.company.repository.CustomerDetailRepository;
import com.cloudkeeper.leasing.company.service.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户详细、开票资料 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerDetailServiceImpl extends BaseServiceImpl<CustomerDetail> implements CustomerDetailService {

    /** 客户详细、开票资料 repository */
    private final CustomerDetailRepository customerDetailRepository;

    @Override
    protected BaseRepository<CustomerDetail> getBaseRepository() {
        return customerDetailRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("taxpayer", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("bankName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("sent", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<CustomerDetail> saveList(List<CustomerDetailDTO> customerDetailList, BaseEntity baseEntity, Boolean isAdd) {
        List<CustomerDetail> customerDetails = new ArrayList<>();
        customerDetailList.forEach(customerDetailDTO -> {
            CustomerDetail customerDetail = customerDetailDTO.convert(CustomerDetail.class);
            customerDetail.setCustomerId(baseEntity.getId());
            if (StringUtils.isEmpty(customerDetail.getId()) && baseEntity.getVersion() != 0) {
                customerDetail.setVersion(baseEntity.getVersion() + 1);
            }
            customerDetail = save(customerDetail);
            customerDetails.add(customerDetail);
        });
        return customerDetails;
    }

    @Override
    public List<CustomerDetail> findAllByCustomerId(String id) {
        return customerDetailRepository.findAllByCustomerId(id);
    }
}