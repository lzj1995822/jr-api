package com.cloudkeeper.leasing.company.service;

import com.cloudkeeper.leasing.bean.company.to.GuaranteeCompanyAllTO;
import com.cloudkeeper.leasing.bean.company.to.GuaranteeCompanyTO;
import com.cloudkeeper.leasing.company.domain.GuaranteeCompany;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanyDTO;
import com.cloudkeeper.leasing.company.dto.guaranteecompany.GuaranteeCompanySearchable;
import com.cloudkeeper.leasing.company.vo.GuaranteeCompanyAllVO;
import com.cloudkeeper.leasing.company.vo.GuaranteeCompanyVO;
import com.querydsl.core.BooleanBuilder;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * 担保公司 service
 * @author asher
 */
public interface GuaranteeCompanyService extends BaseService<GuaranteeCompany> {

    /**
     * 保存接口
     * @param guaranteeCompanyDTO 担保公司DTO
     * @param guaranteeCompany 担保公司对象
     * @return 担保公司
     */
    GuaranteeCompany save(@Nonnull GuaranteeCompanyDTO guaranteeCompanyDTO, @Nonnull GuaranteeCompany guaranteeCompany);

    /**
     * customers转vos
     * @param guaranteeCompanies 担保公司集合
     * @return 担保公司vo集合
     */
    List<GuaranteeCompanyAllVO> toVO(List<GuaranteeCompany> guaranteeCompanies);

    /**
     * 复杂查询（父级字段）
     * @param searchable
     * @return
     */
    BooleanBuilder findByBuilder(GuaranteeCompanySearchable searchable);

    /**
     * 根据历史本版id查询记录
     * @param hisId 历史本版id
     * @return 历史记录
     */
    GuaranteeCompanyAllTO getHisTO(@Nonnull String hisId);

    /**
     * 根据历史本版id查询最新记录
     * @param hisId 历史本版id
     * @return 最新历史记录
     */
    GuaranteeCompanyAllTO getLastHisTO(@Nonnull String hisId);

    /**
     * 根据历史本版id集合查询记录
     * @param ids 历史id集合
     * @return 记录集合
     */
    List<GuaranteeCompanyAllTO> getHisListTO(@Nonnull List<String> ids);

    /**
     * 根据历史本版id集合查询最新记录
     * @param ids 历史id集合
     * @return 最新记录集合
     */
    List<GuaranteeCompanyAllTO> getLastHisListTO(@Nonnull List<String> ids);

}