package com.yangyu.demo.config.security;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.yangyu.demo.config.security.custom.CustomClientDetailsService;
import com.yangyu.demo.config.security.custom.CustomTokenStore;
import com.yangyu.demo.config.security.custom.CustomUserDetailsService;
import com.yangyu.demo.config.security.custom.CustomWebResponseExceptionTranslator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
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

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(customClientDetailsService);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).userDetailsService(customUserDetailsService)
                .tokenEnhancer(new CustomTokenEnhancer())
                .exceptionTranslator(new CustomWebResponseExceptionTranslator()).tokenStore(tokenStore()).tokenServices(tokenService());
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

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;

    @Bean
    public TokenStore tokenStore() {
    //token保存在内存中（也可以保存在数据库、Redis中）。
    //如果保存在中间件（数据库、Redis），那么资源服务器与认证服务器可以不在同一个工程中。
    //注意：如果不保存access_token，则没法通过access_token取得用户信息

     //使用redis存储token
    //  RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
    //  //设置redis token存储中的前缀
    //  redisTokenStore.setPrefix("auth-token:");

     // 使用默认数据库存储token https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
     JdbcTokenStore jdbcTokenStore = new JdbcTokenStore(dataSource1);
    // 自定义tokenStore
    //  CustomTokenStore tokenStore = new CustomTokenStore();
     return jdbcTokenStore;
    }

    @Bean
    public DefaultTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //配置token存储
        tokenServices.setTokenStore(tokenStore());
        //开启支持refresh_token，此处如果之前没有配置，启动服务后再配置重启服务，可能会导致不返回token的问题，解决方式：清除redis对应token存储
        tokenServices.setSupportRefreshToken(true);
        //复用refresh_token
        tokenServices.setReuseRefreshToken(true);
        //token有效期，设置12小时
        tokenServices.setAccessTokenValiditySeconds(12 * 60 * 60);
        //refresh_token有效期，设置一周
        tokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return tokenServices;
    }

    @Bean
    public ApprovalStore approvalStore() throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore());
        return store;
    }
    
}