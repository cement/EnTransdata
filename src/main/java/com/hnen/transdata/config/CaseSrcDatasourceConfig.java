package com.hnen.transdata.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration

@MapperScan(basePackages = "com.hnen.transdata.mapper.casesrc" , sqlSessionTemplateRef = "caseSrcSqlSessionTemplate")
public class CaseSrcDatasourceConfig {

    @ConfigurationProperties(prefix = "datasource.src.case.druid")
    @Bean(name = "caseSrcDataSource")
    public DataSource getSrcDataSource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class)
//                .driverClassName("oracle.jdbc.driver.OracleDriver").password("hdpass").username("tyufu")
//                .url("jdbc:oracle:thin:@//222.240.140.90:65231/prod")
                .build();
    }

    @Bean(name = "caseSrcSqlSessionFactory")
//    @Primary
    public SqlSessionFactory getSrcSqlSessionFactory(@Qualifier("caseSrcDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "caseSrcTransactionManager")
//    @Primary
    public DataSourceTransactionManager getSrcTransactionManager(@Qualifier("caseSrcDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "caseSrcSqlSessionTemplate")
//    @Primary
    public SqlSessionTemplate getSrcSqlSessionTemplate(@Qualifier("caseSrcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
