package com.szss.apollo.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * @author 鼠笑天
 * @date 2018/3/12
 */
@Configuration
@MapperScan("com.szss.apollo.demo.dao")
public class MybatisPlusConfig {

    /**
     * 分页插件
     * 
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}