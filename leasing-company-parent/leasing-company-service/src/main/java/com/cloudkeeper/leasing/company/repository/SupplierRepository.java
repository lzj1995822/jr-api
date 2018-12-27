package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.Supplier;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 供应商 repository
 * @author asher
 */
@Repository
public interface SupplierRepository extends BaseRepository<Supplier> {

}