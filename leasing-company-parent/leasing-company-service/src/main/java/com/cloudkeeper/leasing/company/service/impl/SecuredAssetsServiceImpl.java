package com.cloudkeeper.leasing.company.service.impl;

import com.cloudkeeper.leasing.base.domain.BaseEntity;
import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.company.domain.SecuredAssets;
import com.cloudkeeper.leasing.company.dto.securedassets.SecuredAssetsDTO;
import com.cloudkeeper.leasing.company.repository.SecuredAssetsRepository;
import com.cloudkeeper.leasing.company.service.SecuredAssetsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 担保资产 service
 * @author asher
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecuredAssetsServiceImpl extends BaseServiceImpl<SecuredAssets> implements SecuredAssetsService {

    /** 担保资产 repository*/
    private final SecuredAssetsRepository securedAssetsRepository;

    @Override
    protected BaseRepository<SecuredAssets> getBaseRepository() {
        return securedAssetsRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("owner", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("warrantNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("constructionDate", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("situation", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<SecuredAssets> saveList(List<SecuredAssetsDTO> assetsList, BaseEntity baseEntity, Boolean isAdd) {
        List<SecuredAssets> assetsArrayList = new ArrayList<>();
        if (assetsList != null) {
            assetsList.forEach(SecuredAssetsDTO -> {
                SecuredAssets assets = SecuredAssetsDTO.convert(SecuredAssets.class);
                assets.setGuarantorId(baseEntity.getId());
                if (StringUtils.isEmpty(assets.getId()) && !isAdd) {
                    assets.setVersion(baseEntity.getVersion() + 1);
                }
                assets = save(assets);
                assetsArrayList.add(assets);
            });
        }
        return assetsArrayList;
    }

    @Override
    public List<SecuredAssets> findAllByGuarantorId(@NonNull String guarantorId) {
        return securedAssetsRepository.findAllByGuarantorId(guarantorId);
    }

}