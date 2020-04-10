package com.yangyu.demo.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangyu.demo.base.BaseResponse;
import com.yangyu.demo.config.security.custom.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * SecurityConfig 安全认证的配置
 * 
 * @author yy
 */
@Configurable
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService myUserDetailsService;

    // @Autowired
    // CORSConfiguration CORSConfiguration;

    /**
     * 密码编码器
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 指定路径下的资源需要验证了的用户才能访问
                .antMatchers("/rest/**").authenticated().anyRequest().permitAll()
                // springboot默认登陆的配置
                .and().formLogin().loginProcessingUrl("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                // 禁用 CSRF
                .and().csrf().disable()
                // 设置访问需要验证的资源没有权限时的返回
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()).and().logout()
                .logoutUrl("/logout").logoutSuccessUrl("/logout/success");
    }

    @Component
    public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException authException) throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(BaseResponse.noAuthorities("请先登录")));
        }
    }

    @Component
    public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(BaseResponse.success("登陆成功")));
        }
    }

    @Component
    public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(BaseResponse.error("登陆失败,"+exception.getMessage())));
        }
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /* 自定义验证
        @Component  
        public class CustomAuthenticationProvider implements AuthenticationProvider {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String name = authentication.getName();  
                String password = authentication.getCredentials().toString();
                List<GrantedAuthority> grantedAuths = AuthorityUtils.createAuthorityList("admin");
                if (!name.equals("yangyu")) {
                    throw new AuthenticationCredentialsNotFoundException("你不是yangyu");
                }
                return new UsernamePasswordAuthenticationToken(name, password, grantedAuths); 
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return authentication.equals(UsernamePasswordAuthenticationToken.class);
            }
        }
     */
}