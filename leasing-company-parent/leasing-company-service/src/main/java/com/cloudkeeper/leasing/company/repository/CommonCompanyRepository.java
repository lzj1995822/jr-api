package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.CommonCompany;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 客户、担保公司、供应商父表 repository
 * @author asher
 */
@Repository
public interface CommonCompanyRepository extends BaseRepository<CommonCompany> {

}