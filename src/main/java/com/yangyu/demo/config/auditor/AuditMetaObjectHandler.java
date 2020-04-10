package com.yangyu.demo.config.auditor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.yangyu.demo.entity.source1.User;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
public class AuditMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createDate", Date.class, new Date());
        SecurityContext ctx = SecurityContextHolder.getContext();
        
        User loginUser = (User) ctx.getAuthentication().getPrincipal();
        
        Optional<String> username = Optional.ofNullable(loginUser.getLoginName());
        this.strictInsertFill(metaObject, "createUser", String.class, username.get());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateDate", LocalDateTime.class, LocalDateTime.now());
        
        SecurityContext ctx = SecurityContextHolder.getContext();
        
        User loginUser = (User) ctx.getAuthentication().getPrincipal();
        
        Optional<String> username = Optional.ofNullable(loginUser.getLoginName());
        this.strictInsertFill(metaObject, "updateUser", String.class, username.get());

    }

}