package com.fc.mybatis;

import com.fc.dal.dao.TestMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lize
 *
 */
public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.fc.dal.config");
        context.refresh();
        System.out.println(context.getApplicationName());
        SqlSessionFactory factory = (SqlSessionFactory) context.getBean("sqlSessionFactory");
        System.out.println(factory.openSession().getMapper(TestMapper.class).selectByPrimaryKey(1));
        System.out.println();
        context.close();
    }
}
