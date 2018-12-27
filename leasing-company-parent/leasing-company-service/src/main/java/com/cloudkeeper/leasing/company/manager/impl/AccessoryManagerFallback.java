package com.cloudkeeper.leasing.company.manager.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.bean.identity.to.AccessoryTO;
import com.cloudkeeper.leasing.company.manager.AccessoryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 降级处理
 * @author asher
 */
@Slf4j
@Service
public class AccessoryManagerFallback implements AccessoryManager {

    @Override
    public Result<List<AccessoryTO>> saveList(List<AccessoryTO> accessoryTOS) {
        return Result.of(Result.ResultCode.FAIL.getCode(), "新增附件请求失败！");
    }
}
