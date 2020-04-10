package com.yangyu.demo.service;

import java.util.UUID;

import com.yangyu.demo.dao.source1.AopLogDao;
import com.yangyu.demo.entity.source1.AopLogEntity;

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
    private AopLogDao aopLogDao;

    public void write(String description, String className, String methodName, String args ) {
        AopLogEntity aoplog = new AopLogEntity();
        aoplog.setDescription(description);
        aoplog.setClassName(className);
        aoplog.setMethodName(methodName);
        aoplog.setId(UUID.randomUUID().toString().toUpperCase());
        aoplog.setArgs(args);
        aopLogDao.insertEntity(aoplog);
    }
    
}