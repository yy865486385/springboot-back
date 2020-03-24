package com.yangyu.demo.entity.source1;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yangyu.demo.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * User 用户实体
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_user")
public class User extends BaseEntity{

    private String loginName;

    private String password;

    private String name;

    private String enname;
}