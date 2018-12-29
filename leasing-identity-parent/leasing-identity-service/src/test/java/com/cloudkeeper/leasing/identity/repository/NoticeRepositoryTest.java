package com.cloudkeeper.leasing.identity.repository;

import com.cloudkeeper.leasing.identity.domain.Notice;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 通知 repository 测试
 * @author wj
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class NoticeRepositoryTest {

    /** 测试 entityManager */
    @Autowired
    private TestEntityManager entityManager;

    /** 通知 repository */
    @Autowired
    private NoticeRepository noticeRepository;

    @Before
    public void setUp() {
        Notice notice = new Notice();
        notice = entityManager.persist(notice);
    }

    @After
    public void tearDown() {
        noticeRepository.deleteAll();
    }

    @Test
    public void save() {
        Notice notice = noticeRepository.save(new Notice());
        assertThat(notice).isNotNull();
    }

}