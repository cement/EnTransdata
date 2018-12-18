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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration

@MapperScan(basePackages ={"com.hnen.transdata.mapper.hbase"},sqlSessionTemplateRef = "hbaseDistSqlSessionTemplate")
public class HbaseDistDatasourceConfig {



    @Primary
    @Bean(name = "hbaseDistDataSource")
    @ConfigurationProperties(prefix = "datasource.dist.hbase.druid")

    public DataSource getDistDataSource() {

        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class)
//                .driverClassName("org.apache.phoenix.queryserver.client.Driver").username("admin").password("a")
//                .url("jdbc:phoenix:thin:url=http://hbase.hellight.com:8888;serialization=PROTOBUF")
                .build();
    }


    @Bean(name = "hbaseDistSqlSessionFactory")
    @Primary
       public SqlSessionFactory getDistSqlSessionFactory(@Qualifier("hbaseDistDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    @Bean(name = "hbaseDistTransactionManager")
    @Primary
    public DataSourceTransactionManager getDistransactionManager(@Qualifier("hbaseDistDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "hbaseDistSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate getDistSqlSessionTemplate(@Qualifier("hbaseDistSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
