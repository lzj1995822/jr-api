package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Notice;
import com.cloudkeeper.leasing.base.service.BaseService;
import com.cloudkeeper.leasing.identity.vo.NoticeVO;

import java.util.List;

/**
 * 通知 service
 * @author wj
 */
public interface NoticeService extends BaseService<Notice> {

    /**
     * 处理通知附件
     * @param content
     * @return
     */
    List<NoticeVO> handleFile(List<Notice> content);
}