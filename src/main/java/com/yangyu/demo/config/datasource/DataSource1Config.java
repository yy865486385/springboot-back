package com.yangyu.demo.config.datasource;

import java.util.UUID;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yangyu.demo.config.auditor.AuditMetaObjectHandler;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 数据源1配置
 * 
 * @author yangyu
 * @Date 2020-03-23
 * 
 */
@Configuration
@MapperScan(basePackages = "com.yangyu.demo.mapper.source1", sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DataSource1Config {

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;

    // 使用MybatisSqlSessionFactoryBean，否则无发实现自动填充功能
    @Primary
    @Bean(name = "sqlSessionFactory1")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        // 加载数据源
        mybatisPlus.setDataSource(dataSource1);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 配置填充器
        globalConfig.setMetaObjectHandler(new AuditMetaObjectHandler());
        // id生成策略
        globalConfig.setIdentifierGenerator(new CustomIdGenerator());
        mybatisPlus.setGlobalConfig(globalConfig);
        // TypeHanders
        mybatisPlus.setTypeHandlersPackage("com.yangyu.demo.entity.typehandlers");
        return mybatisPlus;
    }

    // 设置实体id生成策略，继承自mybais plus默认的生成器，重写nextUUID以生成带'-'的uuid
    @Component
    public class CustomIdGenerator extends DefaultIdentifierGenerator {

        @Override
        public String nextUUID(Object entity) {
            return UUID.randomUUID().toString().toUpperCase();
        }
    }

    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}