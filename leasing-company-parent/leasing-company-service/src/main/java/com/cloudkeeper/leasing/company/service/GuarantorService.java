package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.bean.company.to.GuarantorAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuarantorTO;
import com.cloudkeeper.leasing.company.domain.Guarantor;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.guarantor.GuarantorDTO;
import com.cloudkeeper.leasing.company.vo.GuarantorAllVO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 担保人 service
 * @author asher
 */
public interface GuarantorService extends BaseService<Guarantor> {

    /**
     * 保存接口
     * @param guarantorDTO 担保人DTO
     * @param  guarantor 担保人对象
     * @return 担保人
     */
    Guarantor save(@Nonnull GuarantorDTO guarantorDTO,@Nonnull Guarantor guarantor);


    /**
     * 转vo接口
     * @param guarantor 担保人对象
     * @return 担保人vo
     */
    GuarantorAllVO getVo(Guarantor guarantor);

    /**
     * 转vo接口
     * @param guarantor 担保人对象
     * @return 担保人voList
     */
    List<GuarantorAllVO> getListVo(List<Guarantor> guarantor);

    /**
     * 根据历史本版id查询记录
     * @param hisId 历史本版id
     * @return 历史记录
     */
    GuarantorAllTO getHisTO(@Nonnull String hisId);

    /**
     * 根据历史本版id查询最新记录
     * @param hisId 历史本版id
     * @return 最新历史记录
     */
    GuarantorAllTO getLastHisTO(@Nonnull String hisId);

    /**
     * 根据历史本版id集合查询记录
     * @param ids 历史id集合
     * @return 记录集合
     */
    List<GuarantorTO> getHisListTO(@Nonnull List<String> ids);

    /**
     * 根据历史本版id集合查询最新记录
     * @param ids 历史id集合
     * @return 最新记录集合
     */
    List<GuarantorTO> getLastHisListTO(@Nonnull List<String> ids);


}