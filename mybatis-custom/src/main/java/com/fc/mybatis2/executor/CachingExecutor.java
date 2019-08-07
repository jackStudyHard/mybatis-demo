package com.fc.mybatis2.executor;

import com.fc.mybatis2.cache.CacheKey;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lize
 * @date 7/6/19 10:16 PM
 */
public class CachingExecutor implements Executor{
	private Executor delegate;
	private Map<Object, Object> localCache;

	public CachingExecutor(Executor executor) {
		this.delegate = executor;
		this.localCache = new HashMap<>(16);
	}

	@Override
	public <T> T query(Class<T> pojo, String sql, Object[] args) {
		CacheKey cacheKey = createCacheKey(pojo, sql, args);
		if (localCache.containsKey(cacheKey)) {
			System.out.println("======== hit");
			return (T) localCache.get(cacheKey);
		}
		T temp = delegate.query(pojo, sql, args);
		localCache.putIfAbsent(cacheKey, temp);
		return temp;
	}

	private CacheKey createCacheKey(Class<?> pojo, String sql, Object[] args) {
		CacheKey cacheKey = new CacheKey();
		cacheKey.update(pojo.getName());
		cacheKey.update(sql);
		for (Object o : args) {
			cacheKey.update(o);
		}
		return cacheKey;
	}
}
