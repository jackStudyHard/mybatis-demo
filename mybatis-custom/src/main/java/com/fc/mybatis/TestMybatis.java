package com.fc.mybatis;

import com.fc.mybatis2.mapper.Blog;
import com.fc.mybatis2.mapper.BlogMapper;
import com.fc.mybatis2.session.SqlSession;
import com.fc.mybatis2.session.SqlSessionFactory;
import com.fc.mybatis2.session.SqlSessionFactoryBuilder;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 */
public class TestMybatis {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取MapperProxy代理
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);

        System.out.println("第一次查询: " + blog);
        System.out.println();
        blog = mapper.selectBlogById(1);
        System.out.println("第二次查询: " + blog);
    }

}
