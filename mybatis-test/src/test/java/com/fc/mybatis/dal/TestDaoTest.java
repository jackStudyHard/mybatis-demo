package com.fc.mybatis.dal;

import com.fc.dal.dao.TestMapper;
import com.fc.mybatis.BaseTest;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lize
 * @date 6/23/19 11:11 PM
 */
public class TestDaoTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(TestDaoTest.class);
    @Autowired
    private TestMapper mapper;

    @Autowired
    @Qualifier("batchSst")
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test() {
        com.fc.dal.dao.Test test = mapper.selectByPrimaryKey(10);
        log.info("{}", test.getName());
    }

    @Test
    public void insert() {
        com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
        test.setName(com.fc.dal.dao.Test.Name.A.name());
        test.setNums(12);
        log.info("{}", mapper.insert(test));
        log.info("{}",test);
    }

    @Test
    public void insert2() {
        com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
        test.setName(com.fc.dal.dao.Test.Name.C.name());
        test.setNums(12);
        log.info("{}", mapper.insert(test));
        log.info("==========={}", test.getId());
    }

    @Test
    @Transactional
    public void insertBatch() throws Exception {
        long start = System.currentTimeMillis();
        List<com.fc.dal.dao.Test> tests = new ArrayList<com.fc.dal.dao.Test>();
        for (int i = 0; i < 1000; i++) {
            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
            test.setName(com.fc.dal.dao.Test.Name.C.name());
            test.setNums(i);
            mapper.insert(test);
        }
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

//    @Test
//    @Transactional
//    public void insertBatch3() {
//        long start = System.currentTimeMillis();
//        List<com.fc.dal.dao.Test> tests = new ArrayList<com.fc.dal.dao.Test>();
//        for (int i = 0; i < 10; i++) {
//            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
//            test.setName("A");
//            test.setNums(i);
//            tests.add(test);
//        }
//        mapper.insertBatch(tests);
//        log.info("cost {}ms", System.currentTimeMillis() - start);
//    }

    @Test
    @Transactional
    public void insertBatch2() {
        SqlSession session = sqlSessionTemplate
                .getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        TestMapper testMapper = session.getMapper(com.fc.dal.dao.TestMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
            test.setName(com.fc.dal.dao.Test.Name.C.name());
            test.setNums(i);
            testMapper.insert(test);
            if (i % 5 == 0 && i != 0) {
                session.commit();
                session.clearCache();
            }
        }
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

    @Test
    public void update() {
        com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
        test.setId(8);
        test.setName(com.fc.dal.dao.Test.Name.C.name());
        test.setNums(12);
        log.info("{}", mapper.updateByPrimaryKeySelective(test));
    }

    @Test
    @Transactional
    public void selectByPrimaryKey() {
        long start = System.currentTimeMillis();
//        log.info("{}", mapper.selectByPrimaryKey(1));
        log.info("{}", mapper.selectAll());
        log.info("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
//        log.info("{}", mapper.selectByPrimaryKey(1));
        log.info("{}", mapper.selectAll());
        log.info("cost:" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
//        log.info("{}", mapper.selectByPrimaryKey(1));
        log.info("{}", mapper.selectAll());
        log.info("cost:" + (System.currentTimeMillis() - start));
    }

}
