package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;
import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.controller.JrResourceController;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceDTO;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceSearchable;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.vo.JrResourceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传 controller
 * @author hf
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JrResourceControllerImpl extends BaseControllerImpl<JrResource, JrResourceDTO, JrResourceSearchable, JrResourceVO> implements JrResourceController {

    /** 文件上传 service */
    private final JrResourceService jrResourceService;

    @Override
    protected BaseService<JrResource> getBaseService() {
    return jrResourceService;
    }

    @Override
    public List<JrResource> saveFile(MultipartFile[] multipartFile, String type, String id,String countryid) {

        return jrResourceService.saveFile(multipartFile, type, id,countryid);
    }
}