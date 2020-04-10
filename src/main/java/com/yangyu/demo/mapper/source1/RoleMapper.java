package com.yangyu.demo.mapper.source1;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyu.demo.entity.source1.Role;

import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("sqlSessionTemplate1")
public interface RoleMapper extends BaseMapper<Role>{

    @Select("select r.id,r.name from sys_role r join sys_user_roles ur on r.id=ur.role_id where ur.user_id=#{userId}")
    @Results({
		@Result(column = "id", property = "id"),
		@Result(column = "name", property = "name")
	})
    List<Map> findAllByUserId(String userId);
}