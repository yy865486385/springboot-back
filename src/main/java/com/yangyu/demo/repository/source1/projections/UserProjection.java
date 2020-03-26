package com.yangyu.demo.repository.source1.projections;

import java.util.Set;

/**
 * UserProjection 用户返回的投影,只查询用户部分字段和用户的角色的部分字段
 * 
 * @author yangyu Date 2020-03-26
 */
public interface UserProjection {

    String getLoginName();
    String getName();
    String getId();
    String getActive();
    String getEnname();
    Set<Roles> getRoles();
    
    interface Roles {
        String getName();
        String getId();
    }
}