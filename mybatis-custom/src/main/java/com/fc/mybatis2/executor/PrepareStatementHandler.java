package com.fc.mybatis2.executor;

import com.fc.mybatis2.parameter.ParameterHandler;
import com.fc.mybatis2.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @author lize
 * @date 7/6/19 10:20 PM
 */
public class PrepareStatementHandler {
	private ParameterHandler parameterHandler;
	private Configuration configuration;
	private DefaultResultSetHandler resultSetHandler;

	public PrepareStatementHandler(Configuration configuration) {
		this.configuration = configuration;
		this.parameterHandler = new ParameterHandler();
		this.resultSetHandler = new DefaultResultSetHandler();

	}

	public <T> T query(String sql, Object[] args, Class<T> pojo) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			parameterHandler.setPs(ps);
			parameterHandler.handleParameter(args);
			ps.execute();
			return (T) resultSetHandler.queryForResult(pojo, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private Connection getConnection() {
		try {
			Class.forName(configuration.getProperties(Configuration.PropertyNaming.JDBC_DRIVER));
			return DriverManager.getConnection(configuration.getProperties(Configuration.PropertyNaming.JDBC_URL),
					configuration.getProperties(Configuration.PropertyNaming.JDBC_USERNAME),
					configuration.getProperties(Configuration.PropertyNaming.JDBC_PASSWORD));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
