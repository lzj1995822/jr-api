package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.bean.company.to.SupplierAllTO;
import com.cloudkeeper.leasing.company.domain.Supplier;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.supplier.SupplierDTO;
import com.cloudkeeper.leasing.company.vo.SupplierAllVO;
import com.cloudkeeper.leasing.company.vo.SupplierVO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 供应商 service
 * @author asher
 */
public interface SupplierService extends BaseService<Supplier> {

    @Nonnull
    Supplier save(@Nonnull SupplierDTO supplierDTO, Supplier supplier);

    /**
     * 集合转vo集合
     * @param suppliers
     * @return
     */
    List<SupplierAllVO> toVO(List<Supplier> suppliers);

    /**
     * 根据历史记录id获取供应商历史记录
     * @param hisId 历史记录id
     * @return vo
     */
    SupplierAllVO getHisVO(String hisId);

    /**
     * 根据业务主表id拿最新的历史记录
     * @param id 主表id
     * @return vo
     */
    SupplierAllVO getHisVOByBusinessId(String id);

    /**
     * 根据历史记录id获取供应商历史记录
     * @param hisId 历史记录id
     * @return to
     */
    SupplierAllTO getHisTO(String hisId);

    /**
     * 根据业务主表id拿最新的历史记录
     * @param businessId 主表id
     * @return to
     */
    SupplierAllTO getHisTOByBusinessId(String businessId);

    /**
     * 根据历史记录id拿最新的历史记录
     * @param hisId 历史记录id
     * @return to
     */
    SupplierAllTO getLatestHisByHisId(@Nonnull String hisId);
}