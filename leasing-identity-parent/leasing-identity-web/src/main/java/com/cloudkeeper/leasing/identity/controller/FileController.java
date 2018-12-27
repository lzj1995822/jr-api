package com.cloudkeeper.leasing.identity.controller;

import com.cloudkeeper.leasing.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件上传下载 controller
 * @author jerry
 */
@Api(value = "文件传输", tags = "文件传输")
@RequestMapping("/file")
public interface FileController {

    /**
     * 上传
     * @param file 文件
     * @return 文件路径
     * @throws IOException 异常
     */
    @ApiOperation(value = "上传", notes = "上传")
    @PostMapping("/upload")
    Result<String> upload(MultipartFile file) throws IOException;

    /**
     * 获取缩略图路径
     * @param fullPath 文件路径
     * @return 缩略图路径
     */
    @ApiOperation(value = "缩略图路径", notes = "缩略图路径")
    @GetMapping("/thumbImagePath")
    Result<String> getThumbImagePath(@ApiParam(value = "文件路径", required = true) @RequestParam String fullPath);

    /**
     * 下载
     * @param fullPath 文件路径
     * @param response 请求响应
     */
    @ApiOperation(value = "下载", notes = "下载")
    @GetMapping("/download")
    void download(@ApiParam(value = "文件路径", required = true) @RequestParam String fullPath, HttpServletResponse response);
}
