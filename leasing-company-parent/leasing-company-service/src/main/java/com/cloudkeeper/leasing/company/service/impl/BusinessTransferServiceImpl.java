package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import com.cloudkeeper.leasing.company.repository.BusinessTransferRepository;
import com.cloudkeeper.leasing.company.service.BusinessTransferService;
import com.cloudkeeper.leasing.company.service.CustomerTransferService;
import com.cloudkeeper.leasing.company.vo.BusinessTransferVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务员转移、转移客户主记录 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessTransferServiceImpl extends BaseServiceImpl<BusinessTransfer> implements BusinessTransferService {

    /** 业务员转移、转移客户主记录 repository */
    private final BusinessTransferRepository businessTransferRepository;

    /**
     * 转移客户 service
     */
    private CustomerTransferService customerTransferService;

    /** 用户接口*/
    private final PrincipalManager principalManager;

    @Override
    protected BaseRepository<BusinessTransfer> getBaseRepository() {
        return businessTransferRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("reason", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("originId", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("newId", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Nonnull
    @Override
    public BusinessTransfer save(@Nonnull BusinessTransferDTO businessTransferDTO) {
        BusinessTransfer businessTransfer = businessTransferDTO.convert(BusinessTransfer.class);
        businessTransfer = super.save(businessTransfer);

        businessTransfer = handleChildren(businessTransferDTO,  businessTransfer);
        return businessTransfer;
    }

    private BusinessTransfer handleChildren(BusinessTransferDTO businessTransferDTO, BusinessTransfer businessTransfer) {
        businessTransfer.setCustomerTransferList(customerTransferService.saveList(businessTransferDTO, businessTransfer));

        return businessTransfer;
    }

    @Override
    public BusinessTransferVO convertToVO(BusinessTransfer businessTransfer) {
        BusinessTransferVO convert = businessTransfer.convert();
        convert.setOriginName(principalManager.findOne(businessTransfer.getOriginId()).getName());
        convert.setNewName(principalManager.findOne(businessTransfer.getNewId()).getName());
        return convert;
    }

    @Override
    public List<BusinessTransferVO> convertToVOs(List<BusinessTransfer> businessTransfers) {
        return businessTransfers.stream().map(this::convertToVO).collect(Collectors.toList());
    }
}