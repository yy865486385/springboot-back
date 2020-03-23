package com.yangyu.demo.config.datasource;

import java.util.Map;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 

/**
 * 数据源1配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="entityManagerFactory1",
		transactionManagerRef="transactionManager1",
		basePackages={"com.yangyu.demo.repository.source1"})
public class DataSource1Config {

    @Autowired
	@Qualifier("dataSource1")
    private DataSource dataSource;
    
    @Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private HibernateProperties hibernateProperties;
    
    @Primary
	@Bean(name="entityManager1")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder){
		return entityManagerFactoryBean(builder).getObject().createEntityManager();
	}
    
    @Primary
	@Bean(name="entityManagerFactory1")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder){
		return builder
				.dataSource(dataSource)
				.properties(getProperties())
				.packages("com.yangyu.demo.repository.source1")
                .persistenceUnit("PersistentUnit1")
				.build();
	}
	
	public Map<String, ?> getProperties(){
		return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
	}
	
    
    @Primary
	@Bean(name="transactionManager1")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder){
		return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
	}
}