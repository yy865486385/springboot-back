package com.yangyu.demo.service;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.controller.vo.UserAdd;
import com.yangyu.demo.dao.source1.UserDao;
import com.yangyu.demo.entity.source1.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 * 
 * @author yangyu Date 2020-03-25
 */
@Service
@Slf4j
public class UserService extends ServiceImpl<UserDao,User> {

    @Autowired
    private UserDao userDao;

    public BaseResponse getAllUser() {
        return BaseResponse.success(userDao.selectList(null));
	}

	public BaseResponse addUser(UserAdd user) {
        if (!user.valid()) {
            return BaseResponse.error("参数不正确");
        }
        User findUser = userDao.findByLoginName(user.getLoginName());
        if (findUser!=null) {
            return BaseResponse.error("登陆名不能重复");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEnname(user.getEnname());
        newUser.setLoginName(user.getLoginName());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.insert(newUser);
		return BaseResponse.success(user);
    }
    
}