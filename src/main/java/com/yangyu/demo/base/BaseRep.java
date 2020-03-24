package com.yangyu.demo.base;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * 自定义基本的repository
 */
@NoRepositoryBean
public interface BaseRep<T,ID> extends JpaRepository<T,ID> {

    List<T> findAllByOrderByCreateDateAsc();
}