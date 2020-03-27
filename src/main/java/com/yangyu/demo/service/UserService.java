package com.yangyu.demo.service;

import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.controller.vo.UserAdd;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.repository.source1.UserRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 * @author yangyu
 * Date 2020-03-25
 */
@Service
public class UserService {

    @Autowired
    private UserRep userRep;

	public BaseResponse getAllUser() {
		return BaseResponse.success(userRep.findAllByProjections());
	}

	public BaseResponse addUser(UserAdd user) {
        if (!user.valid()) {
            return BaseResponse.error("参数不正确");
        }
        User findUser = userRep.findByLoginName(user.getLoginName());
        if (findUser!=null) {
            return BaseResponse.error("登陆名不能重复");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEnname(user.getEnname());
        newUser.setLoginName(user.getLoginName());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRep.save(newUser);
		return BaseResponse.success(user);
	}
    
}