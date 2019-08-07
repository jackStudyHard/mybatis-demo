package com.fc.mybatis2.executor;

/**
 * @author lize
 * @date 7/6/19 10:15 PM
 */
public interface Executor {
	<T> T query(Class<T> pojo, String sql, Object[] args);
}
