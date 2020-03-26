package com.yangyu.demo.controller;

import com.yangyu.demo.base.BaseResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * NoAuthController
 * @author yangyu
 * Date 2020-03-25
 */
@RestController
@RequestMapping("")
@Api(description = "不需要权限的controller", tags = "不需要权限的controller")
public class NoAuthController {

    @RequestMapping("")
    @ApiOperation(value = "index")
    public BaseResponse index() {
        return BaseResponse.success("index");
    }

    
}