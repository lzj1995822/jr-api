package com.cloudkeeper.leasing.identity.service;

import com.cloudkeeper.leasing.identity.domain.Notice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 通知 service 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeServiceTest {

    /** 通知 service */
    @Autowired
    private NoticeService noticeService;

    @Before
    public void setUp() {
        Notice notice = new Notice();
        notice = noticeService.save(notice);
    }

    @After
    public void tearDown() {
        noticeService.deleteAll();
    }

    @Test
    public void save() {
        Notice notice = noticeService.save(new Notice());
        assertThat(notice).isNotNull();
    }

}