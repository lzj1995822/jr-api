package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.IntentionalCustomer;
import com.cloudkeeper.leasing.company.repository.IntentionalCustomerRepository;
import com.cloudkeeper.leasing.company.service.IntentionalCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class IntentionalCustomerServiceImpl extends BaseServiceImpl<IntentionalCustomer> implements IntentionalCustomerService {

    /** 意向客户Repository*/
    @Autowired
    private IntentionalCustomerRepository intentionalCustomerRepository;

    @Override
    protected BaseRepository<IntentionalCustomer> getBaseRepository() {
        return intentionalCustomerRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("grade", ExampleMatcher.GenericPropertyMatchers.contains());
    }
}
