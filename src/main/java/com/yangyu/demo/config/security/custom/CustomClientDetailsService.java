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
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @author yangyu
 * Date 2020-03-25
 * 
 * CustomClientDetailsService 自定义客户端存储，把客户端数据存在数据库中
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientMapper.findById(clientId);
        if(client==null){
            throw new CustomOAuth2Exception("客户端不存在");
        }
        return client;
    }
}