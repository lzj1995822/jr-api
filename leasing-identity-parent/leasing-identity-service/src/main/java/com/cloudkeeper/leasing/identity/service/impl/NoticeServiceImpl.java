package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.domain.Notice;
import com.cloudkeeper.leasing.identity.domain.QJrResource;
import com.cloudkeeper.leasing.identity.repository.NoticeRepository;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import com.cloudkeeper.leasing.identity.service.NoticeService;
import com.cloudkeeper.leasing.identity.vo.NoticeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 通知 service
 * @author wj
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService {

    /** 通知 repository */
    private final NoticeRepository noticeRepository;

    /** 通知 repository */
    private final JrResourceService jrResourceService;

    @Override
    protected BaseRepository<Notice> getBaseRepository() {
        return noticeRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<NoticeVO> handleFile(List<Notice> content) {
        List<NoticeVO> noticeVOS = new ArrayList<>();
        content.stream().forEach(notice -> {
            NoticeVO noticeVO = new NoticeVO();
            BeanUtils.copyProperties(notice, noticeVO);
            Optional<JrResource> jrResource = jrResourceService.findOne(QJrResource.jrResource.connectId.eq(notice.getId()));
            if (jrResource.isPresent()) {
                noticeVO.setUrl(jrResource.get().getUrl());
            }
            noticeVO.setCreator(notice.getCreator().getName());
            noticeVOS.add(noticeVO);
        });
        return noticeVOS;
    }
}