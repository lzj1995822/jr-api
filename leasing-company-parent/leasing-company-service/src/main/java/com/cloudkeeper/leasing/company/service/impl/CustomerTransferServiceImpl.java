package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.company.domain.Customer;
import com.cloudkeeper.leasing.company.domain.CustomerTransfer;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;
import com.cloudkeeper.leasing.company.repository.CustomerTransferRepository;
import com.cloudkeeper.leasing.company.service.CustomerService;
import com.cloudkeeper.leasing.company.service.CustomerTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 转移d的客户 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerTransferServiceImpl extends BaseServiceImpl<CustomerTransfer> implements CustomerTransferService {

    /** 转移d的客户 repository */
    private final CustomerTransferRepository customerTransferRepository;

    /**
     * 客户 service
     */
    @Autowired
    private CustomerService customerService;

    @Override
    protected BaseRepository<CustomerTransfer> getBaseRepository() {
        return customerTransferRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher();
    }

    @Override
    public List<CustomerTransfer> saveList(BusinessTransferDTO businessTransferDTO, BusinessTransfer businessTransfer) {
        List<CustomerTransfer> customerTransferList = new ArrayList<>();
        businessTransferDTO.getCustomerTransferDTOList().forEach(customerTransferDTO -> {
            Optional<Customer> customerOptional = customerService.findOptionalById(customerTransferDTO.getCustomerId());
            Customer customer = customerOptional.get();
            customer.setCreatedBy(businessTransferDTO.getNewId());
            customerService.save(customer);

            CustomerTransfer customerTransfer = customerTransferDTO.convert(CustomerTransfer.class);
            customerTransfer.setTransferId(businessTransfer.getId());
            customerTransfer = save(customerTransfer);
            customerTransferList.add(customerTransfer);
        });
        return customerTransferList;
    }
}