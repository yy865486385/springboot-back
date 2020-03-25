package com.yangyu.demo.config.security;

import java.util.HashMap;
import java.util.Map;

import com.yangyu.demo.config.security.custom.CustomClientDetailsService;
import com.yangyu.demo.config.security.custom.CustomUserDetailsService;
import com.yangyu.demo.config.security.custom.CustomWebResponseExceptionTranslator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * @author yangyu
 * Date 2020-03-24
 * 
 * AuthorizationServerConfig 认证服务配置
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomClientDetailsService customClientDetailsService;

    // @Autowired
    // private TokenStore tokenStore;

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(customUserDetailsService)
                .tokenEnhancer(new CustomTokenEnhancer())
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    @Component
    public class CustomTokenEnhancer implements TokenEnhancer {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("code", 200);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            // DefaultOAuth2AccessToken x = new DefaultOAuth2AccessToken();
            return accessToken;
        }
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }
}