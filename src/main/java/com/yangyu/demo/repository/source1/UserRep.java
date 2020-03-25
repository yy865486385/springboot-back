package com.yangyu.demo.repository.source1;

import com.yangyu.demo.base.BaseRep;
import com.yangyu.demo.entity.source1.User;

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
    
}