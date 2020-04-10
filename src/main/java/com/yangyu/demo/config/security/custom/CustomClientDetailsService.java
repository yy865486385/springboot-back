package com.yangyu.demo.config.security.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.yangyu.demo.entity.source1.Client;
import com.yangyu.demo.mapper.source1.ClientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * CustomClientDetailsService 自定义客户端存储，把客户端数据存在数据库中
 */
@Service
@Slf4j
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientMapper.findById(clientId);
        if(client==null){
            throw new CustomOAuth2Exception("客户端不存在");
        }
        return new ClientDetails(){
        
            @Override
            public boolean isSecretRequired() {
                // TODO Auto-generated method stub
                return false;
            }
        
            @Override
            public boolean isScoped() {
                // TODO Auto-generated method stub
                return false;
            }
        
            @Override
            public boolean isAutoApprove(String scope) {
                // TODO Auto-generated method stub
                return false;
            }
        
            @Override
            public Set<String> getScope() {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public Set<String> getResourceIds() {
                // TODO Auto-generated method stub
                return client.getResourceIds();
            }
        
            @Override
            public Set<String> getRegisteredRedirectUri() {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public Integer getRefreshTokenValiditySeconds() {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public String getClientSecret() {
                // TODO Auto-generated method stub
                return client.getClientSecret();
            }
        
            @Override
            public String getClientId() {
                // TODO Auto-generated method stub
                return client.getId();
            }
        
            @Override
            public Set<String> getAuthorizedGrantTypes() {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                // TODO Auto-generated method stub
                SimpleGrantedAuthority sa = new SimpleGrantedAuthority("admin");
                
                return new ArrayList<>();
            }
        
            @Override
            public Map<String, Object> getAdditionalInformation() {
                // TODO Auto-generated method stub
                return null;
            }
        
            @Override
            public Integer getAccessTokenValiditySeconds() {
                // TODO Auto-generated method stub
                return null;
            }
        };
    }
}