package com.yangyu.demo.mapper.source1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyu.demo.entity.source1.Client;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * ClientRep 资源客户端实体的Repository
 */
@Mapper
@Qualifier("sqlSessionTemplate1")
public interface ClientMapper extends BaseMapper<Client>{

    @Select("select * from sys_client where id = #{clientId}")
    // @Results(id = "clientMap",value = {
    //     @Result(column = "id",property = "id"),
    //     @Result(column = "resourceIds",property = "resource_ids")
    // })
	Client findById(String clientId);
    
}