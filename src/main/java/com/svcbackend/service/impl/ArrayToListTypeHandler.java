package com.svcbackend.service.impl;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ArrayToListTypeHandler implements TypeHandler<List<String>> {

    @Override
    public void setParameter(java.sql.PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
    }

    @Override
    public List<String> getResult(ResultSet rs, String columnName) throws SQLException {
        return toList(rs.getArray(columnName));
    }

    @Override
    public List<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        return toList(rs.getArray(columnIndex));
    }

    @Override
    public List<String> getResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        return toList(cs.getArray(columnIndex));
    }

    private List<String> toList(Array array) throws SQLException {
        if (array != null) {
            String[] result = (String[]) array.getArray();
            return Arrays.asList(result);
        }
        return null;
    }
}
