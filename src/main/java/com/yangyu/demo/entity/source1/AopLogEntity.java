package com.yangyu.demo.entity.source1;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yangyu.demo.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangyu
 * Date 202-03-27
 * AopLog 日志实体
 */
@Data
@Entity
@Table(name = "sys_log")
@EqualsAndHashCode(callSuper = false)
public class AopLogEntity extends BaseEntity {

    private String description;

    private String className;

    private String methodName;

    private String args;
}