package com.yangyu.demo.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * 多数据原主配置
 */
@Configuration
public class DataSourceMainConfig {


    @Bean(name="dataSource1")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.datasource1")
	public DataSource dataSource(){
		// return DataSourceBuilder.create().build();
		return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
	@Bean(name="properties1")
	@ConfigurationProperties(prefix="spring.datasource.datasource1")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}
	
	
	@Bean(name="dataSource2")
	@ConfigurationProperties(prefix="spring.datasource.datasource2")
	public DataSource otherDataSource(){
		// return DataSourceBuilder.create().build();
		return otherDataSourceProperties().initializeDataSourceBuilder().build();
	}
    
    @Bean(name="properties2")
	@ConfigurationProperties(prefix="spring.datasource.datasource2")
	public DataSourceProperties otherDataSourceProperties(){
		return new DataSourceProperties();
	}
    
}