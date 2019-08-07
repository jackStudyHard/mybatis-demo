package com.fc.mybatis2.executor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lize
 * @date 7/6/19 10:20 PM
 */
public class DefaultResultSetHandler<T> {

	public DefaultResultSetHandler() {
	}

	public T queryForResult(Class<T> pojo, PreparedStatement ps) {
		ResultSet rs = null;
		try {
			T result = pojo.newInstance();
			rs = ps.getResultSet();
			if (rs.next()) {
				for (Field field : pojo.getDeclaredFields()) {
					setValue(field, result, rs);
				}
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private void setValue(Field field, T result, ResultSet rs) {
		String methodName = "set" + firstWordCapital(field.getName());
		try {
			Method method = result.getClass().getDeclaredMethod(methodName, field.getType());
			method.invoke(result, getResult(rs, field));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}


	private String firstWordCapital(String word){
		String first = word.substring(0, 1);
		String tail = word.substring(1);
		return first.toUpperCase() + tail;
	}

	private Object getResult(ResultSet rs, Field field) throws SQLException {
		Class type = field.getType();
		String dataName = HumpToUnderline(field.getName());
		if (Integer.class == type ) {
			return rs.getInt(dataName);
		} else if (Long.class == type){
			return rs.getLong(dataName);
		} else {
			return rs.getString(dataName);
		}
	}

	// 数据库下划线转Java驼峰命名
	public static String HumpToUnderline(String para){
		StringBuilder sb=new StringBuilder(para);
		int temp=0;
		if (!para.contains("_")) {
			for(int i=0;i<para.length();i++){
				if(Character.isUpperCase(para.charAt(i))){
					sb.insert(i+temp, "_");
					temp++;
				}
			}
		}
		return sb.toString().toUpperCase();
	}
}