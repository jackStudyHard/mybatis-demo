package com.fc.dal.resultmap;

import com.fc.dal.dao.Author;
import lombok.Data;

/**
 * @author lize
 * @date 6/23/19 10:11 PM
 */
@Data
public class BlogResultMap {

    private Integer bid;

    private String name;

    private Author author;

}
