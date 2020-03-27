package com.yangyu.demo.controller;

import com.yangyu.demo.aop.AopLog;
import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.controller.vo.UserAdd;
import com.yangyu.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * UserController
 * @author yangyu
 * Date 2020-03-25
 */
@RequestMapping("rest/users")
@RestController
@Api(description = "用户数据",tags = "用户数据")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    @ApiOperation(value = "获取所有用户数据")
    @AopLog("获取所有用户数据")
    public BaseResponse getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("")
    @ApiOperation(value = "新增用户")
    @AopLog("新增用户")
    public BaseResponse addUser(@RequestBody UserAdd user) {
        return userService.addUser(user);
    }
    
}