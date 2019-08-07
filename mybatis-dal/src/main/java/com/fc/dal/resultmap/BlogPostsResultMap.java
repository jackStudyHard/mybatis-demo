package com.fc.dal.resultmap;

import com.fc.dal.dao.Posts;
import lombok.Data;

import java.util.List;

/**
 * @author lize
 * @date 6/23/19 10:11 PM
 */
@Data
public class BlogPostsResultMap{

    private Integer bid;

    private String name;

    private Integer authorId;

    private List<Posts> posts;

}
