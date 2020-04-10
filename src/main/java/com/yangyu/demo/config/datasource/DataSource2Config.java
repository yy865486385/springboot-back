package com.yangyu.demo.config.datasource;

import javax.sql.DataSource;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangyu
 * Date 2020-03-23
 * 
 * 数据源2配置
 */
@Configuration
@MapperScan(basePackages = "com.yangyu.demo.dao.source2", sqlSessionTemplateRef = "sqlSessionTemplate2")
public class DataSource2Config {

    @Autowired
    @Qualifier("dataSource2")
    private DataSource dataSource2;

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource2);
        return sqlSessionFactoryBean;
    }

	// @Bean(name = "dataSource2")
    // @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    // public DataSource dataSource() {
    //     return DataSourceBuilder.create().build();
	// }
	
	// @Bean(name = "sqlSessionFactory2")
    // public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
    //     SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    //     bean.setDataSource(dataSource);
    //     //  bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/db1/*.xml"));
    //     return bean.getObject();
	// }
	
	// @Bean(name = "transactionManager2")
    // public DataSourceTransactionManager transactionManager(@Qualifier("datasource2") DataSource dataSource) {
    //     return new DataSourceTransactionManager(dataSource);
	// }
	
	@Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}