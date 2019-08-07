package com.fc.mybatis2.session;

import com.fc.mybatis2.executor.Executor;

/**
 * @author lize
 * @date 7/6/19 9:29 AM
 */
public class SqlSession {

	private Configuration configuration;
	private Executor executor;

	public SqlSession(Configuration configuration) {
		this.configuration = configuration;
		this.executor = configuration.newExecutor();
	}

	public <T> T select(Class<T> pojo, String sql, Object args[]) {
		return executor.query(pojo, sql, args);
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public <T> T getMapper(Class<T> clazz) {
		return configuration.getMapper(clazz, this);
	}
}
