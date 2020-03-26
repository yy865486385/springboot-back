package com.yangyu.demo.service;

import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.entity.source1.Role;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.repository.source1.UserRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 * @author yangyu
 * Date 2020-03-25
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRep userRep;

	public BaseResponse getAllUser() {
        User user = userRep.findByLoginName("yangyu");
        for (Role role : user.getRoles()) {
            log.info(role.getName());
        }
		return BaseResponse.success(userRep.findAll());
	}
    
}