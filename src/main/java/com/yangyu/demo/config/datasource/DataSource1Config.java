package com.yangyu.demo.config.datasource;

import java.io.IOException;
import java.util.UUID;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yangyu.demo.config.auditor.AuditMetaObjectHandler;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

/**
 * 数据源1配置
 * 
 * @author yangyu
 * @Date 2020-03-23
 * 
 */
@Configuration
@MapperScan(basePackages = "com.yangyu.demo.dao.source1", sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DataSource1Config {

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;

    // @Primary
    // @Bean(name = "sqlSessionFactory1")
    // public SqlSessionFactoryBean sqlSessionFactoryBean() {
    // SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    // sqlSessionFactoryBean.setDataSource(dataSource1);
    // // 设置entity和数据库字段的映射为下划线形式
    // org.apache.ibatis.session.Configuration configuration = new
    // org.apache.ibatis.session.Configuration();
    // configuration.setMapUnderscoreToCamelCase(true);
    // sqlSessionFactoryBean.setConfiguration(configuration);
    // return sqlSessionFactoryBean;
    // }

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
        globalConfig.setIdentifierGenerator(new CustomIdGenerator());
        mybatisPlus.setGlobalConfig(globalConfig);

        return mybatisPlus;
    }

    // 设置实体id生成策略，numberid未配置
    @Component
    public class CustomIdGenerator implements IdentifierGenerator {
        @Override
        public String nextUUID(Object entity) {
            return UUID.randomUUID().toString().toUpperCase();
        }

        @Override
        public Number nextId(Object entity) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    // @Bean(name = "transactionManager1")
    // @Primary
    // public DataSourceTransactionManager
    // transactionManager(@Qualifier("datasource1") DataSource dataSource) {
    // return new DataSourceTransactionManager(dataSource);
    // }

    @Bean(name = "sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}