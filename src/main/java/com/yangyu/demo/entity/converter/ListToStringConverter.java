package com.yangyu.demo.entity.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

import org.springframework.util.StringUtils;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * 将List<String> 转化成字符串入库，按","来区分
 * 
 */
public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        
        if (attribute == null || attribute.isEmpty()){
            return "";
        }
       StringBuilder sb = new StringBuilder();
       attribute.stream().limit(attribute.size()-1).forEach(s -> sb.append(s).append(","));
       sb.append((attribute.toArray())[attribute.size()-1]);
       return sb.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)){
            return new ArrayList<>();
        }
        String[] data = dbData.split(",");
        return   Arrays.stream(data).map(String::trim).collect(Collectors.toList());
    }

}