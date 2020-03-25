package com.yangyu.demo.config.security.custom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author yangyu
 * Date 2020-03-24
 * 
 * 重新序列化OAuth2Exception
 */
@JsonSerialize(using = CustomOAuthExceptionJacksonSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {

    public CustomOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}