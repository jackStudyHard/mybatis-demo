package com.fc.mybatis2.session;

/**
 * @author lize
 * @date 7/6/19 9:28 AM
 */
public class SqlSessionFactory {
	Configuration configuration;

	public SqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	public SqlSession openSession() {
		return new SqlSession(configuration);
	}
}
