package com.cloudkeeper.leasing.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamSource;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载工具类
 * @author jerry
 */
@Slf4j
public class FileUtil {

    /**
     * 下载
     * @param fileName 文件名
     * @param resource 文件源
     * @param response 请求响应
     */
    public static void download(@Nonnull String fileName, @Nonnull InputStreamSource resource, @Nonnull HttpServletResponse response) {
        InputStream inputStream;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            return;
        }
        download(fileName, inputStream, response);
    }

    /**
     * 下载
     * @param fileName 文件名
     * @param filePath 文件路径
     * @param response 请求响应
     */
    public static void download(@Nonnull String fileName, @Nonnull String filePath, @Nonnull HttpServletResponse response) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.warn(e.getMessage(), e);
            return;
        }
        FileUtil.download(fileName, inputStream, response);
    }

    /**
     * 下载
     * @param fileName 文件名
     * @param inputStream 文件流
     * @param response 请求响应
     */
    public static void download(@Nonnull String fileName, @Nonnull InputStream inputStream, @Nonnull HttpServletResponse response) {
        response.reset();
        response.setContentType("multipart/form-data");
        OutputStream outputStream = null;
        try {
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            outputStream = response.getOutputStream();
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
    }

    /**
     * 下载
     * @param fileName 文件名
     * @param bytes 二进制文件
     * @param response 请求响应
     */
    public static void download(@Nonnull String fileName, @Nonnull byte[] bytes, @Nonnull HttpServletResponse response) {
        response.reset();
        response.setContentType("multipart/form-data");
        OutputStream outputStream = null;
        try {
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    }
}
