package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.base.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件上传 service
 * @author hf
 */
public interface JrResourceService extends BaseService<JrResource> {

    /**
     * 保存文件
     * @return
     */
    List<JrResource> saveFile(MultipartFile[] multipartFile, String type, String id);
}