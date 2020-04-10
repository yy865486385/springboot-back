package com.yangyu.demo.entity.source1;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_role")
public class Role extends BaseEntity{

    private String name;
    
}