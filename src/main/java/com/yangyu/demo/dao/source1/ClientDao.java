package com.yangyu.demo.dao.source1;

import java.util.Optional;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyu.demo.entity.source1.Client;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * ClientRep 资源客户端实体的Repository
 */
@Mapper
@Qualifier("sqlSessionTemplate1")
public interface ClientDao extends BaseMapper<Client>{

    @Select("select * from sys_client where id = #{clientId}")
    // @Results(id = "clientMap",value = {
    //     @Result(column = "id",property = "id"),
    //     @Result(column = "resourceIds",property = "resource_ids")
    // })
	Client findById(String clientId);
    
}