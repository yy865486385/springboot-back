package com.yangyu.demo.entity.source1;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yangyu.demo.base.BaseEntity;
import com.yangyu.demo.entity.typehandlers.SetToStringTypeHandler;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Client
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName(value = "sys_client")
public class Client extends BaseEntity implements ClientDetails {

    private String description;

    private Boolean secretRequired;

    private String clientSecret;

    private Boolean scoped;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Boolean autoApprove;

    @TableField(typeHandler = SetToStringTypeHandler.class)
    private Set<String> scope;

    @TableField(typeHandler = SetToStringTypeHandler.class)
    private Set<String> authorizedGrantTypes;

    @TableField(typeHandler = SetToStringTypeHandler.class)
    private Set<String> registeredRedirectUri;

    @TableField(typeHandler = SetToStringTypeHandler.class)
    private Set<String> resourceIds;

    @TableField(typeHandler = SetToStringTypeHandler.class)
    private Set<String> authorities;

    @Override
    public String getClientId() {
        return this.getId();
    }

    @Override
    public boolean isSecretRequired() {
        return this.secretRequired;
    }

    @Override
    public boolean isScoped() {
        return this.scoped;
    }

    @Override
    public boolean isAutoApprove(final String scope) {
        return this.autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        final Map<String, Object> additional = new HashMap<>();
        additional.put("description", this.description);
        return additional;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new HashSet<>();
        this.authorities.forEach(a->auth.add(new SimpleGrantedAuthority(a)));
        return auth;
    }
}