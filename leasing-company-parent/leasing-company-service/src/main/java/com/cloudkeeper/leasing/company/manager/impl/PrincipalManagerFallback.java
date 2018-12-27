package com.cloudkeeper.leasing.company.manager.impl;

import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.manager.PrincipalManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 manager 降级处理
 * @author jerry
 */
@Service
public class PrincipalManagerFallback implements PrincipalManager {

    @Override
    public PrincipalTO findOne(String id) {
        return null;
    }

    @Override
    public List<PrincipalTO> findAllTO(List<String> ids) {
        return new ArrayList<>();
    }

}
