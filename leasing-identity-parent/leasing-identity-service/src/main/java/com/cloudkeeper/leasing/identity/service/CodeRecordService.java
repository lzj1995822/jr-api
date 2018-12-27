package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.CodeConfig;
import com.cloudkeeper.leasing.identity.domain.CodeRecord;
import com.cloudkeeper.leasing.base.service.BaseService;

/**
 * 编码生成记录 service
 * @author asher
 */
public interface CodeRecordService extends BaseService<CodeRecord> {

    /**
     * 初始化编码生成记录对象
     * @param codeConfig 编码配置对象
     * @return 编码生成对象
     */
    CodeRecord init(CodeConfig codeConfig);

    /**
     * 根据编码生成记录对象和编码配置对象生成编码
     * @param codeRecord 编码记录对象
     * @param codeConfig 编码配置对象
     * @return 编码
     */
    String generateCode(CodeRecord codeRecord, CodeConfig codeConfig);

    /**
     * 生成某一编号配置的的编号
     * @param configId 编号配置id
     * @return
     */
    String getCode(String configId);
}