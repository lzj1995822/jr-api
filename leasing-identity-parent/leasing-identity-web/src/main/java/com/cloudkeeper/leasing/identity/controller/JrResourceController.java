package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.controller.BaseController;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceDTO;
import com.cloudkeeper.leasing.identity.dto.jrresource.JrResourceSearchable;
import com.cloudkeeper.leasing.identity.vo.JrResourceVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传 controller
 * @author hf
 */
@Api(value = "文件上传", tags = "文件上传")
@RequestMapping("/jrResource")
public interface JrResourceController extends BaseController<JrResourceDTO, JrResourceSearchable, JrResourceVO> {

    @PostMapping("/fileUpload")
    List<JrResource> saveFile(MultipartFile[] multipartFile, String type, String id,String countryid);
}