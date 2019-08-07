package com.fc.mybatis2.session;

import com.fc.mybatis2.annotation.Bean;
import com.fc.mybatis2.binding.MapperRegistry;
import com.fc.mybatis2.executor.CachingExecutor;
import com.fc.mybatis2.executor.Executor;
import com.fc.mybatis2.executor.SimpleExecutor;
import com.fc.mybatis2.plugin.Interceptor;
import com.fc.mybatis2.plugin.InterceptorChain;

import java.io.File;
import java.util.*;

/**
 * @author lize
 * @date 7/6/19 9:26 AM
 */
public class Configuration {

	private static final ResourceBundle mybatisConfig = ResourceBundle.getBundle("mybatis");
	private static final ResourceBundle sqlConfig = ResourceBundle.getBundle("sql");


	private MapperRegistry mapperRegistry; // 维护接口 与 mapperproxy
	private Map<String, String> mapperStatements; // 定义维度 与 sql 映射
	private InterceptorChain chain;

	public Configuration() {
		mapperRegistry = new MapperRegistry();
		mapperStatements = new HashMap<>(16);
		chain = new InterceptorChain();
		parse();
	}

	public Executor newExecutor() {
		try {
			Executor executor = new CachingExecutor(new SimpleExecutor(this));
			return (Executor) chain.pluginAll(executor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getMappedStatement(String methodDefinition) {
		return mapperStatements.get(methodDefinition);
	}

	public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
		return mapperRegistry.getMapper(clazz, sqlSession);
	}

	public String getProperties(PropertyNaming propertyName) { // 获取全局配置文件
		return mybatisConfig.getString(propertyName.property);
	}

	private void parse() {
		// 解析 sql.properties
		sqlConfig.keySet().forEach(o -> {
			mapperStatements.computeIfAbsent(o, a -> {
				String value = sqlConfig.getString(a);
				return value.split("--")[0];
			});
		});
		// 解析Mapper
		parseMapper();
		// 解析Plugin
		parsePlugin();
	}

	private void parsePlugin() {
		String[] pluginPaths = getProperties(PropertyNaming.PLUGIN_PATH).split(",");
		for (String pluginPath : pluginPaths) {
			try {
				Class<?> clazz = Class.forName(pluginPath);
				if (Interceptor.class.isAssignableFrom(clazz)) {
					chain.addInterceptors((Interceptor) clazz.newInstance());
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}

	private void parseMapper() {
		String basePackage = getProperties(PropertyNaming.MAPPER_PATH);
		String classPath = basePackage.replace(".", File.separator);
		Set<Class<?>> clazzs = new HashSet<>();
		getAllClasses(new File(this.getClass().getResource("/").getPath() + classPath), clazzs);
		clazzs.stream().filter(o -> o.isInterface() && o.isAnnotationPresent(Bean.class)).forEach(o -> {
			mapperRegistry.addMapper(o, o.getDeclaredAnnotation(Bean.class).value());
		});
	}

	private void getAllClasses(File classPath, Set<Class<?>> clazzs) {
		if (classPath.isDirectory()) {
			for (File subFile : classPath.listFiles()) {
				getAllClasses(subFile, clazzs);
			}
		} else {
			if (classPath.getName().endsWith(".class")) { // class 文件
				try {
					clazzs.add(
							Thread.currentThread().getContextClassLoader()
									.loadClass(classPath.getPath().replace(this.getClass().getResource("/").getPath(), "")
											.replace(File.separator, ".").replace(".class", "")));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public enum PropertyNaming {
		JDBC_URL("jdbc.url"),
		JDBC_DRIVER("jdbc.driver"),
		JDBC_USERNAME("jdbc.username"),
		JDBC_PASSWORD("jdbc.password"),
		CACHE_ENABLED("cache.enabled"),
		PLUGIN_PATH("plugin.path"),
		MAPPER_PATH("mapper.path")
		;
		private String property;

		PropertyNaming(String property) {
			this.property = property;
		}
	}

}
