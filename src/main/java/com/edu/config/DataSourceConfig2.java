package com.edu.config;

import com.edu.config.vo.Demo2Config;
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
@MapperScan(value = "com.edu.mapper.demo2",sqlSessionTemplateRef = "demo2SqlSessionTemplate")
public class DataSourceConfig2 {


    @Bean(name = "demo2DataSource")
    public DataSource demo2DataSource (Demo2Config demo2Config) throws SQLException {
        System.out.println(demo2Config);
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(demo2Config.getJdbcUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(demo2Config.getPassword());
        mysqlXaDataSource.setUser(demo2Config.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(demo2Config.getUniqueResourceName());
        xaDataSource.setMinPoolSize(demo2Config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(demo2Config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(demo2Config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(demo2Config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(demo2Config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(demo2Config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(demo2Config.getMaxIdleTime());
        xaDataSource.setTestQuery(demo2Config.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "demo2SqlSessionFactory")
    public SqlSessionFactory demo2SqlSessionFactory (@Qualifier("demo2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    @Bean(name = "demo2SqlSessionTemplate")
    public SqlSessionTemplate demo2SqlSessionTemplate (@Qualifier("demo2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
