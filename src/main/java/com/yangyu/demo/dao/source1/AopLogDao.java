package com.yangyu.demo.dao.source1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyu.demo.entity.source1.AopLogEntity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author yangyu
 * Date 2020-03-27
 * 
 * AopLogRep
 */
@Mapper
@Qualifier("sqlSessionTemplate1")
public interface AopLogDao extends BaseMapper<AopLogEntity>{

    @Insert("insert into sys_log(id,active,class_name,description,method_name,args) values(#{id},#{active},#{className},#{description},#{methodName},#{args})")
	public int insertEntity(AopLogEntity aopLogEntity);
    
}