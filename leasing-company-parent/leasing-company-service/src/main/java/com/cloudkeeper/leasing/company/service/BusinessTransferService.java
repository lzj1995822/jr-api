package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.company.domain.BusinessTransfer;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.businesstransfer.BusinessTransferDTO;
import com.cloudkeeper.leasing.company.vo.BusinessTransferVO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 业务员转移、转移客户主记录 service
 * @author asher
 */
public interface BusinessTransferService extends BaseService<BusinessTransfer> {

    /**
     * 转移客户新增接口
     * @param businessTransferDTO
     * @return
     */
    @Nonnull
    BusinessTransfer save(@Nonnull BusinessTransferDTO businessTransferDTO);

    /**
     * 客户转移转vo
     * @param businessTransfer 客户转移
     * @return
     */
    BusinessTransferVO convertToVO(BusinessTransfer businessTransfer);

    /**
     * 转vo集合
     * @param businessTransfers 客户转移集合
     * @return
     */
    List<BusinessTransferVO> convertToVOs(List<BusinessTransfer> businessTransfers);
}