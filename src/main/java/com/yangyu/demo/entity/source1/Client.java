package com.yangyu.demo.entity.source1;

import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yangyu.demo.base.BaseEntity;
import com.yangyu.demo.entity.typehandlers.SetToStringTypeHandler;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
}