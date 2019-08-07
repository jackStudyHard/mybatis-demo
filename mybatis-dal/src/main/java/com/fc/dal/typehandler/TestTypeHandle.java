package com.fc.dal.typehandler;

import com.fc.dal.dao.Test;
import com.fc.dal.resultmap.BlogResultMap;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeException;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lize
 * @date 6/23/19 13:11 PM
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TestTypeHandle extends BaseTypeHandler<Test.Name> {
    public TestTypeHandle() {
    }

    public void setNonNullParameter(PreparedStatement ps, int i, Test.Name parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    public Test.Name getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (value == null) return null;
        return Test.Name.valueOf(rs.getString(columnName));
    }

    public Test.Name getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return Test.Name.valueOf(rs.getString(columnIndex));
    }

    public Test.Name getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Test.Name.valueOf(cs.getString(columnIndex));
    }


}
