package com.yangyu.demo.config.security.custom;

import java.util.Optional;

import com.yangyu.demo.entity.source1.Client;
import com.yangyu.demo.repository.source1.ClientRep;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ClientRep clientRep;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> client = clientRep.findById(clientId);
        if(!client.isPresent()){
            throw new CustomOAuth2Exception("客户端不存在");
        }
        return client.get();
    }
}