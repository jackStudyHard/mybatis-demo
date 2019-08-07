package com.fc.mybatis2.parameter;

import com.fc.mybatis.plugin.Interceptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author lize
 * @date 7/6/19 10:22 PM
 */
public class ParameterHandler {
	private PreparedStatement ps ;

	public ParameterHandler() {
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public void handleParameter(Object[] args) {
		try {
			int i = 1;
			for (Object o : args) {
				if (o instanceof Integer) {
					ps.setInt(i, ((Integer) o).intValue());
				} else if (o instanceof Long) {
					ps.setLong(i, (Long) o);
				} else {
					ps.setString(i, (String) o);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
