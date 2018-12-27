package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.Guarantor;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 担保人 repository
 * @author asher
 */
@Repository
public interface GuarantorRepository extends BaseRepository<Guarantor> {

}