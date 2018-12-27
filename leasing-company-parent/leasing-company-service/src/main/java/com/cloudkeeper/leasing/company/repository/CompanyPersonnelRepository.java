package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.company.domain.CompanyPersonnel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司相关人员 repository
 * @author asher
 */
@Repository
public interface CompanyPersonnelRepository extends BaseRepository<CompanyPersonnel> {

    /**
     * 根据公司id及人员类型查询相关类型人员
     * @param companyId
     * @param type
     * @return
     */
    List<CompanyPersonnel> findAllByAndCompanyIdAndType(String companyId, String type);

    /**
     * 根据公司id查找公司相关人员
     * @param id
     * @return
     */
    List<CompanyPersonnel> findAllByAndCompanyId(String id);
}