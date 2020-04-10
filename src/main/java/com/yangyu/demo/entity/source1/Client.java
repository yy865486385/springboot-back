package com.yangyu.demo.entity.source1;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yangyu.demo.base.BaseEntity;
import com.yangyu.demo.entity.converter.SetToStringConverter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Client
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName(value = "sys_client")
public class Client extends BaseEntity{

    private String description;

    private Boolean secretRequired;

    private String clientSecret;

    private Boolean scoped;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;
    
    private Boolean autoApprove;

    private Set<String> scope;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Set<String> resourceIds;

    private Set<String> authorities;


    // @Override
    // public boolean isSecretRequired() {
    //     return this.secretRequired;
    // }

    // @Override
    // public boolean isScoped() {
    //     return this.scoped;
    // }

    // @Override
    // public boolean isAutoApprove(String scope) {
    //     return this.autoApprove;
    // }

    // @Override
    // public Map<String, Object> getAdditionalInformation() {
    //     Map<String,Object> additionalInformation = new HashMap<>();
    //     additionalInformation.put("description", this.description);
    //     return additionalInformation;
    // }

    // @Override
    // public String getClientId() {
    //     return this.getId();
    // }

    // @Override
    // public Collection<GrantedAuthority> getAuthorities() {
    //     Set<GrantedAuthority> authorities = new HashSet<>();
    //     for (String authority : this.authorities) {
    //         SimpleGrantedAuthority sga = new SimpleGrantedAuthority(authority);
    //         authorities.add(sga);
    //     }
    //     return authorities;
    // }

    // @Override
    // public Set<String> getResourceIds() {
    //     // TODO Auto-generated method stub
    //     return this.resourceIds;
    // }

    // @Override
    // public String getClientSecret() {
    //     // TODO Auto-generated method stub
    //     return this.clientSecret;
    // }

    // @Override
    // public Set<String> getScope() {
    //     // TODO Auto-generated method stub
    //     return this.scope;
    // }

    // @Override
    // public Set<String> getAuthorizedGrantTypes() {
    //     // TODO Auto-generated method stub
    //     return this.authorizedGrantTypes;
    // }

    // @Override
    // public Set<String> getRegisteredRedirectUri() {
    //     // TODO Auto-generated method stub
    //     return this.registeredRedirectUri;
    // }

    // @Override
    // public Integer getAccessTokenValiditySeconds() {
    //     // TODO Auto-generated method stub
    //     return this.accessTokenValiditySeconds;
    // }

    // @Override
    // public Integer getRefreshTokenValiditySeconds() {
    //     // TODO Auto-generated method stub
    //     return this.refreshTokenValiditySeconds;
    // }
}