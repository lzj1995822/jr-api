package com.cloudkeeper.leasing.company.manager;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.identity.to.AccessoryTO;
import com.cloudkeeper.leasing.company.manager.impl.AccessoryManagerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(value = "API-IDENTITY", fallback = AccessoryManagerFallback.class)
public interface AccessoryManager {

    @PostMapping(value = "/accessory/addList")
    Result<List<AccessoryTO>> saveList(List<AccessoryTO> accessoryTOS);
}
