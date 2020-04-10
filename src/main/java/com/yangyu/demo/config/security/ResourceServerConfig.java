package com.yangyu.demo.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangyu.demo.base.BaseResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author yangyu
 *Date 2020-03-24
 *ResourceServerConfig 资源服务配置
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    
    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("rid").stateless(true);
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/rest/**").and().authorizeRequests().antMatchers("/rest/**")
                .authenticated();
    }

    @Component
    public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException authException) throws ServletException {
            Throwable cause = authException.getCause();
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                if (cause instanceof InvalidTokenException) {
                    response.getWriter()
                            .write(new ObjectMapper().writeValueAsString(BaseResponse.invalidToken("无效的token")));
                } else {
                    response.getWriter()
                            .write(new ObjectMapper().writeValueAsString(BaseResponse.noAuthorities("请申请授权")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Component
    public class CustomAccessDeniedHandler implements AccessDeniedHandler {

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response,
                AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            try {
                response.getWriter().write(new ObjectMapper().writeValueAsString(BaseResponse.noAuthorities("权限不足")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}