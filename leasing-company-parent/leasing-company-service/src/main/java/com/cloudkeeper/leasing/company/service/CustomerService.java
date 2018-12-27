package com.cloudkeeper.leasing.company.service;


import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.bean.company.to.CustomerAllTO;
import com.cloudkeeper.leasing.company.domain.Customer;
import com.cloudkeeper.leasing.company.dto.customer.CustomerDTO;
import com.cloudkeeper.leasing.company.vo.CustomerAllVO;
import com.cloudkeeper.leasing.company.vo.CustomerVO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 组织角色 service
 * @author jerry
 */
public interface CustomerService extends BaseService<Customer> {

    /**
     * 重新customer保存接口
     * @param customerDTO
     * @return
     */
    Customer save(@Nonnull CustomerDTO customerDTO, Customer customer);

    /**
     * customers转vos
     * @param customers
     * @return
     */
    List<CustomerAllVO> toVO(List<Customer> customers);



    /**
     * 获取客户下的历史记录vo包含子
     * @param hisId 历史记录id
     * @return
     */
    CustomerAllVO getHisVO(@Nonnull String hisId);

    /**
     * 获取客户历史记录vo版汉子
     * @param businessId 业务id
     * @return
     */
    CustomerAllTO getHisTOByBusinessId(String businessId);

    /**
     * 获取客户下的历史记录to包含子
     * @param hisId 历史记录id
     * @return
     */
    CustomerAllTO getHisTO(@Nonnull String hisId);

    /**
     * 获取客户历史记录to版汉子
     * @param businessId 业务id
     * @return
     */
    CustomerAllVO getHisVOByBusinessId(String businessId);

    CustomerAllTO getLatestHisByHisId(@Nonnull String hisId);
}
