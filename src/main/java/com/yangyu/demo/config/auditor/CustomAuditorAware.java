package com.yangyu.demo.config.auditor;

import java.util.Optional;

import com.yangyu.demo.entity.source1.User;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author
 * @Date 2020-03-26
 * @desc CustomAuditorAware 定义实体添加和修改时候自动填入操作的用户
 */
@Configuration
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        User loginUser = (User) ctx.getAuthentication().getPrincipal();
        return Optional.ofNullable(loginUser.getLoginName());

    }

}