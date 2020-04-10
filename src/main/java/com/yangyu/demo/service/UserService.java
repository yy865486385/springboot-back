package com.yangyu.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.controller.vo.UserAdd;
import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.mapper.source1.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 
 * @author yangyu Date 2020-03-25
 */
@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    @Autowired
    private UserMapper userMapper;

    public BaseResponse getAllUser() {
        return BaseResponse.success(userMapper.findAll());
	}

	public BaseResponse addUser(UserAdd user) {
        if (!user.valid()) {
            return BaseResponse.error("参数不正确");
        }
        User findUser = userMapper.findByLoginName(user.getLoginName());
        if (findUser!=null) {
            return BaseResponse.error("登陆名不能重复");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEnname(user.getEnname());
        newUser.setLoginName(user.getLoginName());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(newUser);
		return BaseResponse.success(user);
    }
    
}