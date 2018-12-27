package com.cloudkeeper.leasing.company.manager;

import com.cloudkeeper.leasing.bean.identity.to.PrincipalTO;
import com.cloudkeeper.leasing.company.manager.impl.PrincipalManagerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户 manager
 * @author jerry
 */
@FeignClient(value = "API-IDENTITY", fallback = PrincipalManagerFallback.class)
public interface PrincipalManager {

    /**
     * 查询用户
     * @param id 用户id
     * @return 用户
     */
    @GetMapping(value = "/principal/to/{id}")
    PrincipalTO findOne(@PathVariable("id") String id);

    /**
     * 查询用户集合
     * @param ids 用户id
     * @return 用户集合
     */
    @GetMapping(value = "/principal/to/ids")
    List<PrincipalTO> findAllTO(@RequestBody List<String> ids);

}
