package com.fc.mybatis.executor;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 */
public interface Executor {
    <T> T query(String statement, Object[] parameter, Class pojo);
}
