package com.yangyu.demo.entity.source1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yangyu.demo.base.BaseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangyu Date 2020-03-23
 * 
 *         User 用户实体
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_user")
public class User extends BaseEntity implements UserDetails {

    private String loginName;

    private String password;

    private String name;

    private String enname;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    // 帐户是否过期
    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    // 帐户是否被冻结
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    // 帐号是否可用
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}