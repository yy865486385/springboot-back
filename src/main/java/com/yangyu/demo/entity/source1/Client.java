package com.yangyu.demo.entity.source1;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yangyu.demo.base.BaseEntity;
import com.yangyu.demo.entity.converter.SetToStringConverter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Client
 */
@Data
@Table(name = "sys_client")
@Entity
@EqualsAndHashCode(callSuper = false)
public class Client extends BaseEntity implements ClientDetails {

    private String description;
    @Convert(converter = SetToStringConverter.class)
    private Set<String> resourceIds;
    private Boolean secretRequired;

    private String clientSecret;

    private Boolean scoped;

    @Convert(converter = SetToStringConverter.class)
    private Set<String> scope;

    @Convert(converter = SetToStringConverter.class)
    private Set<String> authorizedGrantTypes;

    @Convert(converter = SetToStringConverter.class)
    private Set<String> registeredRedirectUri;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;
    
    private Boolean autoApprove;

    
    @Convert(converter = SetToStringConverter.class)
    private Set<String> authorities;


    @Override
    public boolean isSecretRequired() {
        return this.secretRequired;
    }

    @Override
    public boolean isScoped() {
        return this.scoped;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return this.autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String,Object> additionalInformation = new HashMap<>();
        additionalInformation.put("description", this.description);
        return additionalInformation;
    }

    @Override
    public String getClientId() {
        return this.getId();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String authority : this.authorities) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(authority);
            authorities.add(sga);
        }
        return authorities;
    }
}