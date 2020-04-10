package com.yangyu.demo.entity.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

/**
 * 将Set<String> 转化成字符串入库，按","来区分
 * 
 * @author yangyu
 * @Date 2020-03-25
 */
@MappedTypes({Set.class})
@MappedJdbcTypes({JdbcType.NVARCHAR})
public class SetToStringTypeHandler implements TypeHandler<Set<String>> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, convertToDatabaseColumn(parameter));

    }

    @Override
    public Set<String> getResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return convertToEntityAttribute(columnValue);
    }

    @Override
    public Set<String> getResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return convertToEntityAttribute(columnValue);
    }

    @Override
    public Set<String> getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return convertToEntityAttribute(columnValue);
    }

    private String convertToDatabaseColumn(Set<String> attribute) {

        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        attribute.stream().limit(attribute.size() - 1).forEach(s -> sb.append(s).append(","));
        sb.append((attribute.toArray())[attribute.size() - 1]);
        return sb.toString();
    }

    private Set<String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)) {
            return new HashSet<>();
        }
        String[] data = dbData.split(",");
        return Arrays.stream(data).map(String::trim).collect(Collectors.toSet());
    }

}