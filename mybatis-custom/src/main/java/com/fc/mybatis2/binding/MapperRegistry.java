package com.fc.mybatis2.binding;

import com.fc.mybatis2.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lize
 * @date 7/6/19 9:27 AM
 */
public class MapperRegistry {

	private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>(16);

	public void addMapper(Class<?> mapperClass, Class pojo) {
		knownMappers.putIfAbsent(mapperClass, new MapperProxyFactory(mapperClass, pojo));
	}

	public <T> T getMapper(Class<T> mapperClass, SqlSession sqlSession) {
		MapperProxyFactory proxyFactory = knownMappers.get(mapperClass);
		return (T) proxyFactory.newInstance(sqlSession);
	}


}
