package com.fc.mybatis2.session;

/**
 * @author lize
 * @date 7/6/19 9:28 AM
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactoryBuilder() {}

	public SqlSessionFactory build() {
		return new SqlSessionFactory(new Configuration());
	}
}
