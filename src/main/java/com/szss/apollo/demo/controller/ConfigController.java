package com.szss.apollo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.szss.apollo.demo.dao.UserDAO;
import com.szss.apollo.demo.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 鼠笑天
 * @date 2018/8/13
 */
@RestController
@Slf4j
public class ConfigController {

    @ApolloConfig
    private Config config2;

    @Autowired
    private UserDAO userDAO;

    /**
     * 获取配置参数
     * 
     * @return
     */
    @RequestMapping("/config")
    public String hello() {
        Config config = ConfigService.getAppConfig();
        String value = config.getProperty("test", "1");
        log.info("test:{}", value);
        UserDO userDO = userDAO.selectById(1);
        log.info("userDO:{}", userDO);
        return "config";
    }
}
