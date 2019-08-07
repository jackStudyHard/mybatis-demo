package com.fc.dal.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author lize
 * @date 6/23/19 9:46 PM
 */
@Configuration
@ComponentScan(basePackages = "com.fc.dal")
@PropertySource("classpath:config/app.properties")
public class DataSourceConfig {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Bean
	public Filter statFilter() {
		StatFilter statFilter = new StatFilter();
		statFilter.setSlowSqlMillis(2000);
		statFilter.setLogSlowSql(true);
		return statFilter;
	}

	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
	public DataSource getDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setConnectionProperties("config.decrypt=true");
		dataSource.setFilters("stat");
		dataSource.setMaxActive(20);
		dataSource.setInitialSize(20);
		dataSource.setMaxWait(60000);
		dataSource.setMinIdle(1);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setValidationQuery("SELECT 'x'");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		dataSource.setPoolPreparedStatements(false);
		dataSource.setMaxOpenPreparedStatements(20);
		dataSource.setProxyFilters(Arrays.asList(statFilter()));
		dataSource.setConnectionErrorRetryAttempts(5);
		return dataSource;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer loadProperties() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
