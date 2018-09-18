package com.szss.apollo.demo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.context.annotation.Import;

/**
 * @author 鼠笑天
 * @date 2018/8/14
 */
@EnableApolloConfig("application")
@Import(RefreshAutoConfiguration.class)
@RefreshScope
@Configuration
public class DataSourceConfig {
    /**
     *
     */
    @ApolloConfig("application")
    private Config config;

    /**
     * 数据源
     * 
     * @return DataSource
     * @throws SQLException 异常
     */
    @RefreshScope
    @Bean("dataSource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(config.getProperty("spring.datasource.url",
            "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8"));
        dataSource.setUsername(config.getProperty("spring.datasource.username", "root"));
        dataSource.setPassword(config.getProperty("spring.datasource.password", "123456"));
        dataSource.setDriverClassName(config.getProperty("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver"));

        dataSource.setFilters(config.getProperty("spring.datasource.druid.filters", "mergeStat"));

        dataSource.setMaxActive(config.getIntProperty("spring.datasource.druid.maxActive", 20));
        dataSource.setMinIdle(config.getIntProperty("spring.datasource.druid.minIdle", 1));
        dataSource.setInitialSize(config.getIntProperty("spring.datasource.druid.initialSize", 1));

        dataSource.setTimeBetweenEvictionRunsMillis(
            config.getIntProperty("spring.datasource.druid.timeBetweenEvictionRunsMillis", 60000));
        dataSource.setMinEvictableIdleTimeMillis(
            config.getIntProperty("spring.datasource.druid.minEvictableIdleTimeMillis", 300000));

        dataSource.setTestWhileIdle(config.getBooleanProperty("spring.datasource.druid.testWhileIdle", true));
        dataSource.setTestOnBorrow(config.getBooleanProperty("spring.datasource.druid.testOnBorrow", false));
        dataSource.setTestOnReturn(config.getBooleanProperty("spring.datasource.druid.testOnReturn", false));

        return dataSource;
    }
}
