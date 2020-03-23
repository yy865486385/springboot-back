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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * 数据源2配置
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "managerFactory2",
    transactionManagerRef = "transactionManager2",
    basePackages = {"com.yangyu.demo.repository.source2" })
public class DataSource2Config {

	@Autowired
	@Qualifier("dataSource2")
	private DataSource dataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private HibernateProperties hibernateProperties;

	
	@Bean(name = "entityManager2")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryBean(builder).getObject().createEntityManager();
	}

	@Bean(name = "entityManagerFactory2")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource)
				.properties(getProperties())
				.packages("com.yangyu.demo.entity.source2")
				.persistenceUnit("PersistentUnit2")
				.build();
	}

	public Map<String, ?> getProperties() {
		return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
	}

	@Bean(name = "transactionManager2")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryBean(builder).getObject());
	}
}