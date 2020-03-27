package com.yangyu.demo.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yangyu
 * Date 2020-03-27
 * 定义一个方法级别的@UserActionLog注解
 * 用于记录用户的操作
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopLog {
    String value() default "";
}