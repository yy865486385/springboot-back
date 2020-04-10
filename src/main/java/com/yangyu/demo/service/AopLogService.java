package com.yangyu.demo.service;

import java.util.UUID;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyu.demo.entity.source1.AopLogEntity;
import com.yangyu.demo.mapper.source1.AopLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yangyu
 * Date 2020-03-27
 * 
 * AopLogService
 */
@Service
public class AopLogService extends ServiceImpl<AopLogMapper,AopLogEntity>{

    @Autowired
    private AopLogMapper aopLogMapper;

    @Transactional
    public void write(String description, String className, String methodName, String args ) {
        AopLogEntity aoplog = new AopLogEntity();
        aoplog.setDescription(description);
        aoplog.setClassName(className);
        aoplog.setMethodName(methodName);
        aoplog.setId(UUID.randomUUID().toString().toUpperCase());
        aoplog.setArgs(args);
        aopLogMapper.insert(aoplog);
    }
    
}