package com.fc.mybatis.dal;

import com.fc.dal.dao.Blog;
import com.fc.dal.dao.BlogMapper;
import com.fc.dal.dao.Posts;
import com.fc.dal.resultmap.BlogPostsResultMap;
import com.fc.dal.resultmap.BlogResultMap;
import com.fc.mybatis.BaseTest;
import org.apache.ibatis.binding.MapperProxy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lize
 * @date 6/23/19 10:31 PM
 */
public class BlogMapperTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(BlogMapperTest.class);
    @Autowired
    private BlogMapper mapper;

    @Test
    public void selectBlogAuthor() { // one to one
        BlogResultMap resultMap = mapper.selectBlogAuthor(1);
        System.out.println(resultMap);
    }

    @Test
    public void selectByBlogId() {
        List<Posts> resultMap = mapper.selectByBlogId(1);
        System.out.println(resultMap);
    }

    @Test
    public void selectBlogPostsNestedResult() {//one to many
        BlogPostsResultMap resultMap = mapper.selectBlogPosts2(1);
        System.out.println(resultMap);
    }

    @Test
    public void selectBlogPostsNestedQuery() throws InterruptedException {//one to many
        BlogPostsResultMap resultMap = mapper.selectBlogPosts(1);
        System.out.println();
        System.out.println(resultMap.getPosts().get(0).getPostName());
        Thread.sleep(5000);
        System.out.println(resultMap.getName());
    }

    @Test
    public void selectBlogPostsNestedQueryN1() throws InterruptedException {//one to many
        List<BlogPostsResultMap> resultMap = mapper.selectBlogPostsList(0);
        System.out.println(resultMap.get(0).getName());
        Thread.sleep(5000);
        System.out.println(resultMap.get(0).getPosts().get(0).getPostName());
        System.out.println(resultMap.get(1).getPosts().get(0).getPostName());
    }

    @Test
    public void selectByAuthorIdNestedQuery() {//one to one
//        BlogResultMap resultMap = mapper.selectBlogAuthor(1);
//        System.out.println(resultMap);
        Blog blog = new Blog();
        blog.setName("ddd");
        mapper.insert(blog);
    }

    @Test
    public void selectByAuthorId2() {//one to one
        BlogResultMap resultMap = mapper.selectBlogAuthor2(1);
        System.out.println(resultMap);
    }

}
