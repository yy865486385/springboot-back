package com.yangyu.demo.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.yangyu.demo.service.AopLogService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author yangyu
 * Date 2020-03-27
 * 
 * AopConfig aop日志的配置
 * 
 */
@Aspect
@Component
public class AopConfig {

    @Autowired
    private AopLogService aopLogService;

    // 表示匹配带有自定义注解的方法
    @Pointcut("@annotation(com.yangyu.demo.aop.AopLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        try {
            result = point.proceed();
            insertLog(point); // 在执行结束后插入日志
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    public void insertLog(ProceedingJoinPoint point) {
        
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String description = "";
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());
        // 注解注解上的描述
        AopLog userActionLog = method.getAnnotation(AopLog.class);

        if (userActionLog != null) {
            description = userActionLog.value();
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        /* 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        */
        aopLogService.write(description, className, methodName, args);
    }

    
}