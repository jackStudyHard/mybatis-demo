package com.fc.mybatis2.executor;

import com.fc.mybatis2.session.Configuration;

/**
 * @author lize
 * @date 7/6/19 10:16 PM
 */
public class SimpleExecutor implements Executor{
	private Configuration configuration;

	public SimpleExecutor(Configuration configuration) {
		this.configuration = configuration;
	}
	@Override
	public <T> T query(Class<T> pojo, String sql, Object[] args) {
		return new PrepareStatementHandler(configuration).query(sql, args, pojo);
	}

}
