package com.yangyu.demo.service;

import com.yangyu.demo.entity.source1.AopLogEntity;
import com.yangyu.demo.repository.source1.AopLogRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yangyu
 * Date 2020-03-27
 * 
 * AopLogService
 */
@Service
public class AopLogService {

    @Autowired
    private AopLogRep aopLogRep;

    public void write(String description, String className, String methodName, String args ) {
        AopLogEntity aoplog = new AopLogEntity();
        aoplog.setDescription(description);
        aoplog.setClassName(className);
        aoplog.setMethodName(methodName);
        aoplog.setArgs(args);
        aopLogRep.save(aoplog);
    }
    
}