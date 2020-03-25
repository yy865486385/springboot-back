package com.yangyu.demo.config.security.custom;

import com.yangyu.demo.entity.source1.User;
import com.yangyu.demo.repository.source1.UserRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author 杨宇
 * Date 2020-03-24
 * 
 * 自定用户DetailService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("");
        }
        return user;
    }

}