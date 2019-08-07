package com.fc.mybatis.dal;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fc.dal.dao.TestExample;
import com.fc.dal.dao.TestMapper;
import com.fc.mybatis.BaseTest;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tools.ant.taskdefs.condition.IsFalse;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lize
 * @date 6/23/19 10:11 PM
 */
public class TestMapperTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(TestMapperTest.class);
    @Autowired
    private TestMapper mapper;

    @Autowired
    @Qualifier("batchSst")
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Test
    @Transactional
    public void select() {
//        SqlSession sqlSession =  sqlSessionTemplate.getSqlSessionFactory().openSession();
//        long start = System.currentTimeMillis();
//        System.out.println(mapper.selectByPrimaryKey(1));
//        sqlSession.selectOne("selectSOne");
//        System.out.println("cost "+ (System.currentTimeMillis() - start));
//        start = System.currentTimeMillis();
//        sqlSession.selectOne("selectSOne");
//        SqlSession session = sqlSessionFactory.openSession();
//        com.fc.dal.dao.Test test  = session.getMapper(TestMapper.class).selectByPrimaryKey(1);
//        System.out.println(test);
//        System.out.println("+++++++++++" + session.getMapper(TestMapper.class).selectByPrimaryKey(1));
//        System.out.println("cost "+ (System.currentTimeMillis() - start));

        mapper.selectByPrimaryKey(9573);
        mapper.selectByPrimaryKey(9573);
    }

    @Test
    public void select0() {
//        TestMapper mapper2 = sqlSessionFactory.openSession().getMapper(TestMapper.class);
//        com.fc.dal.dao.Test test  = mapper2.selectByPrimaryKey(1);
//        test.setName("一级缓存中级测试");
//        mapper2.updateByPrimaryKey(test);
//        System.out.println("=========" + test);
//        mapper.selectByPrimaryKey(1);
        log.info("---------------- {}", mapper.selectByPrimaryKey(9573));
    }

    @Test
    @Transactional
    public void insert() {
        com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
//        test.setName("TTTTTTT");
        test.setNums(12);
        log.info("----------- {}", mapper.insert(test));
        log.info("{}", test.getId());
        System.out.println(com.fc.dal.dao.Test.Name.valueOf("D"));
    }

    @Test
    @Transactional
    public void insertBatch() {
        long start = System.currentTimeMillis();
        List<com.fc.dal.dao.Test> tests = new ArrayList<com.fc.dal.dao.Test>();
        for (int i = 0; i < 100; i++) {
            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
//            test.setName("A");
            test.setNums(i);
            mapper.insert(test);
        }
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

    @Test
    @Transactional
    public void insertBatchAppendSql() {
        long start = System.currentTimeMillis();
        Set<com.fc.dal.dao.Test> tests = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
            test.setName(com.fc.dal.dao.Test.Name.C.name());
            test.setNums(i);
            tests.add(test);
        }
        mapper.insertBatch(tests);
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

    @Test
    @Transactional
    public void insertBatchExType() throws Exception{
        SqlSession session = sqlSessionTemplate
                .getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        TestMapper testMapper = session.getMapper(com.fc.dal.dao.TestMapper.class);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            com.fc.dal.dao.Test test = new com.fc.dal.dao.Test();
            test.setName(com.fc.dal.dao.Test.Name.C.name());
            test.setNums(i);
            mapper.insert(test);
            if (i % 5 == 0 && i != 0) {
                session.commit();
                session.clearCache();
            }
        }
        log.info("cost {}ms", System.currentTimeMillis() - start);
    }

    @Test
    public void pagination() {
        PageHelper.startPage(1,20);
//        List<com.fc.dal.dao.Test> tests =  sqlSessionTemplate.selectList("selectAll",null,new RowBounds(2,10));
        List<com.fc.dal.dao.Test> tests =  mapper.selectAll();
        System.out.println(tests);
        PageInfo<Object> page =PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
            public void doSelect() {
                mapper.selectAll();
            }
        });
        System.out.println(page);
        log.info("+++++++++++ {}", page.getList());
    }

    @Test
    public void example() {
        TestExample example = new TestExample();
        example.setLimitClause("0,10");
        List<com.fc.dal.dao.Test> tests = mapper.selectByExample(example);
        System.out.printf(tests.toString());
    }

    @Test
    public void testAnnotation() throws IllegalAccessException, NoSuchFieldException {
//        System.out.println(this.getClass().getField("REQUEST_ID").get(this.getClass()));
    }

}
