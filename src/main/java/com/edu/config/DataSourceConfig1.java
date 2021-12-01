package com.edu.config;

import com.edu.config.vo.Demo1Config;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(value = "com.edu.mapper.demo1",sqlSessionTemplateRef = "demo1SqlSessionTemplate")
public class DataSourceConfig1 {

    @Bean(name = "demo1DataSource")
    public DataSource demo1DataSource (Demo1Config demo1Config) throws SQLException {
        System.out.println(demo1Config);
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(demo1Config.getJdbcUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(demo1Config.getPassword());
        mysqlXaDataSource.setUser(demo1Config.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(demo1Config.getUniqueResourceName());
        xaDataSource.setMinPoolSize(demo1Config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(demo1Config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(demo1Config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(demo1Config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(demo1Config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(demo1Config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(demo1Config.getMaxIdleTime());
        xaDataSource.setTestQuery(demo1Config.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "demo1SqlSessionFactory")
    public SqlSessionFactory demo1SqlSessionFactory (@Qualifier("demo1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    @Bean(name = "demo1SqlSessionTemplate")
    public SqlSessionTemplate demo1SqlSessionTemplate (@Qualifier("demo1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
