package com.cloudkeeper.leasing.identity.controller.impl;

import com.cloudkeeper.leasing.base.model.Result;
import com.cloudkeeper.leasing.base.utils.FileUtil;
import com.cloudkeeper.leasing.identity.controller.FileController;
import com.github.tobato.fastdfs.domain.MetaData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * 文件上传下载 controller
 * @author jerry
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileControllerImpl implements FileController {

    /** 文件服务器 客户端 */
    private final FastFileStorageClient storageClient;

    /** 缩略图 配置 */
    private final ThumbImageConfig thumbImageConfig;

    /** 图片后缀集合 */
    private static final Set<String> IMAGE_PREFIX_SET = ImmutableSet.of("JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP");

    /** 文件名称 */
    private static final String META_DATA_NAME_FILE_NAME = "FILE_NAME";

    @Override
    public Result<String> upload(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePrefix = FilenameUtils.getExtension(fileName);
        StorePath storePath;
        if (IMAGE_PREFIX_SET.contains(filePrefix.toUpperCase())) {
            // 图片上传并且生成缩略图
            storePath = this.storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), filePrefix, Collections.singleton(new MetaData(META_DATA_NAME_FILE_NAME, fileName)));
        } else {
            // 普通文件上传
            storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), filePrefix, Collections.singleton(new MetaData(META_DATA_NAME_FILE_NAME, fileName)));
        }
        return Result.of(Result.ResultCode.OK.getCode(), Result.ResultCode.OK.getMsg(), storePath.getFullPath());
    }

    @Override
    public Result<String> getThumbImagePath(@RequestParam String fullPath) {
        StorePath storePath = StorePath.praseFromUrl(fullPath);
        return Result.of(Result.ResultCode.OK.getCode(), Result.ResultCode.OK.getMsg(), thumbImageConfig.getThumbImagePath(storePath.getFullPath()));
    }

    @Override
    public void download(@RequestParam String fullPath, HttpServletResponse response) {
        StorePath storePath = StorePath.praseFromUrl(fullPath);
        Set<MetaData> metaDataSet = storageClient.getMetadata(storePath.getGroup(), storePath.getPath());
        String fileName = metaDataSet.stream().findFirst()
                .filter(metaData -> META_DATA_NAME_FILE_NAME.equals(metaData.getName()))
                .map(MetaData::getValue)
                .orElse(FilenameUtils.getName(fullPath));
        byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
        FileUtil.download(fileName, bytes, response);
    }
}
