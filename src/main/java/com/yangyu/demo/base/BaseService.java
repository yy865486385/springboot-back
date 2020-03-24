package com.yangyu.demo.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * 基本的service
 */
public interface BaseService<T extends BaseEntity,ID> {

    @Autowired
    BaseRep<T, ID> getRep();

    T beforeAdd(T entity);

    default T save(T entity) {
        entity = beforeAdd(entity);
        return getRep().save(entity);
    }

    default List<T> findAll() {
        return getRep().findAll();
    }
    
}