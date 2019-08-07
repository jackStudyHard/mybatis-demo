package com.fc.mybatis2.mapper;

import com.fc.mybatis2.annotation.Bean;

@Bean(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    Blog selectBlogById(Integer bid);

}
