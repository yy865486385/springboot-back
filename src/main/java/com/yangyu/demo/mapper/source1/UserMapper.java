package com.yangyu.demo.mapper.source1;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyu.demo.entity.source1.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * UserMapper 用户实体的mapper
 * 
 * @author yangyu 
 * 
 * @Date 2020-03-23
 * 
 */
@Mapper
@Qualifier("sqlSessionTemplate1")
public interface UserMapper extends BaseMapper<User> {

	@Select("select * from sys_user where login_name=#{username}")
	User findByLoginName(String username);

	@Select("select id,active,login_name,name from sys_user")
	@Results(id = "userMain", value =  {
		@Result(column = "id", property = "id"),
		@Result(column = "active", property = "active"),
		@Result(column = "login_name", property = "loginName"),
		@Result(column = "name", property = "name"),
		@Result(column="id",property="roles",many = @Many(select = "com.yangyu.demo.mapper.source1.RoleMapper.findAllByUserId",fetchType = FetchType.DEFAULT))
	})
	List<Map> findAll();

	@Insert("insert into sys_user(id,active,enname,name,password,login_name) values(newid(),1,#{enname},#{name},#{password},#{loginName})")
	public int insertEntity(User user);

}