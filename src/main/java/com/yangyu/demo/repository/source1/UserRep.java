package com.yangyu.demo.repository.source1;

import java.util.Collection;

import com.yangyu.demo.base.BaseRep;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.repository.source1.projections.UserProjection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * UserRep 用户实体的Repository
 */
@Repository
public interface UserRep extends BaseRep<User,String>{

	User findByLoginName(String username);

	@Query("select u from User u")
	Collection<UserProjection> findAllByProjections();
    
}