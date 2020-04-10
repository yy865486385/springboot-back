package com.yangyu.demo.config.auditor;

import java.util.Date;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yangyu.demo.entity.source1.User;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AuditMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 添加创建时间
        this.strictInsertFill(metaObject, "createDate", Date.class, new Date());
        // 添加创建用户
        String createUser = "";
        SecurityContext ctx = SecurityContextHolder.getContext();
        try {
            User loginUser = (User) ctx.getAuthentication().getPrincipal();
            createUser = loginUser.getLoginName();
        } catch (NullPointerException e) {
            // throw new NoAuthenticationException("没有操作用户信息，无法新增");
        }

        this.strictInsertFill(metaObject, "createUser", String.class, createUser);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 添加更新时间
        this.strictUpdateFill(metaObject, "updateDate", Date.class, new Date());
        // 添加更新用户
        String updateUser = "";
        SecurityContext ctx = SecurityContextHolder.getContext();
        try {
            User loginUser = (User) ctx.getAuthentication().getPrincipal();
            updateUser = loginUser.getLoginName();
        } catch (NullPointerException e) {
            // throw new NoAuthenticationException("没有操作用户信息，无法修改");
        }
        this.strictUpdateFill(metaObject, "updateUser", String.class, updateUser);
    }

}