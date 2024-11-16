package com.svcbackend.service.impl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class JsonTypeHandler extends BaseTypeHandler<List<Map<String, Object>>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Map<String, Object>> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (Exception e) {
            throw new SQLException("Error converting parameter to JSON", e);
        }
    }

    @Override
    public List<Map<String, Object>> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        try {
            return json != null ? objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {}) : null;
        } catch (Exception e) {
            throw new SQLException("Error reading JSON from database", e);
        }
    }

    @Override
    public List<Map<String, Object>> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        try {
            return json != null ? objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {}) : null;
        } catch (Exception e) {
            throw new SQLException("Error reading JSON from database", e);
        }
    }

    @Override
    public List<Map<String, Object>> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        try {
            return json != null ? objectMapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {}) : null;
        } catch (Exception e) {
            throw new SQLException("Error reading JSON from database", e);
        }
    }
}