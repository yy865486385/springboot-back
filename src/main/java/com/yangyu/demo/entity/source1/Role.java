package com.yangyu.demo.entity.source1;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yangyu.demo.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Role
 * @author yangyu
 * Date 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity{

    private String name;
    
}