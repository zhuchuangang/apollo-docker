package com.szss.apollo.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 鼠笑天
 * @date 2018/8/13
 */
@Slf4j
@Configuration
public class AppConfig {
    /**
     * 指定命名空间配置
     */
    @ApolloConfig("application")
    private Config config;
    /**
     * 指定命名空间配置
     */
    @ApolloConfig("TEST1.testNameSpace")
    private Config config2;
    /**
     * 注入指定配置
     */
    @Value("${spring.datasource.url:1}")
    private String name;
    /**
     * 注入指定配置
     */
    @Value("${cccc:test}")
    private String name2;

    @Autowired
    private RefreshScope refreshScope;

    /**
     * 命名空间application配置发生变化
     * 
     * @param changeEvent 变化事件
     */
    @ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        log.info(name);
        log.info("name2,{}", name2);
        log.info("config,{}", config.getProperty("test", "1"));
        log.info("config2,{}", config2.getProperty("test2", "1"));

        ConfigChange change = changeEvent.getChange("spring.datasource.url");
        log.info(String.format("Found change - key: %s, oldValue: %s," + " newValue: %s, changeType: %s",
            change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
        refreshScope.refreshAll();
    }

    /**
     * 命名空间application配置发生变化
     * 
     * @param changeEvent 变化事件
     */
    @ApolloConfigChangeListener("TEST1.testNameSpace")
    private void another2OnChange(ConfigChangeEvent changeEvent) {
        log.info(name);
        log.info(name2);
        ConfigChange change = changeEvent.getChange("test");
        log.info(String.format("Found change - key: %s, oldValue: %s," + " newValue: %s, changeType: %s",
            change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }

}
