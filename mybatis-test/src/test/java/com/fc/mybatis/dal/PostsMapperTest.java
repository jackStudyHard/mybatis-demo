package com.fc.mybatis.dal;

import com.fc.dal.dao.PostsMapper;
import com.fc.mybatis.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lize
 * @date 6/23/19 10:17 PM
 */
public class PostsMapperTest extends BaseTest {
    private final static Logger log = LoggerFactory.getLogger(PostsMapperTest.class);
    @Autowired
    private PostsMapper mapper;

//    @Test
//    public void select() {//嵌套结果
//        List<Posts> resultMap = mapper.selectByBlogId(1);
//        System.out.println(resultMap);
//    }

}
