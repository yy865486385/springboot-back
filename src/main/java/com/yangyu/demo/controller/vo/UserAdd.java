package com.yangyu.demo.controller.vo;

import org.springframework.util.StringUtils;

import lombok.Data;

/**
 * @author yangyu
 * Date 2020-03-26
 * UserAdd添加user时的object
 * 
 */
@Data
public class UserAdd {

    private String loginName;

    private String password;

    private String name;

    private String enname;

    public Boolean valid() {
        if (StringUtils.isEmpty(this.loginName)) {
            return false;
        }
        if (StringUtils.isEmpty(this.password)) {
            return false;
        }
        if (StringUtils.isEmpty(this.name)) {
            return false;
        }
        if (StringUtils.isEmpty(this.enname)) {
            return false;
        }
        return true;
    }
}