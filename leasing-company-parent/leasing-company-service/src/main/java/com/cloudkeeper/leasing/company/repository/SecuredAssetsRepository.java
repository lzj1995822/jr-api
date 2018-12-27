package com.cloudkeeper.leasing.company.repository;

import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 担保资产 repository
 * @author asher
 */
@Repository
public interface SecuredAssetsRepository extends BaseRepository<SecuredAssets> {

    List<SecuredAssets>  findAllByGuarantorId(String guarantorId);

}