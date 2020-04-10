package com.yangyu.demo.entity.source1;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yangyu.demo.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangyu
 * Date 202-03-27
 * AopLog 日志实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_log")
public class AopLogEntity extends BaseEntity {

    private String description;

    private String className;

    private String methodName;

    private String args;
}