package com.sika.code.demo.interfaces.business.user.controller;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static com.alibaba.nacos.api.config.ConfigType.YAML;

/**
 * PatternProperties
 *
 * @author : daiqi
 * @date : 2023-10-18
 */
@Component
@Data
//@NacosConfigurationProperties(dataId = "test1", autoRefreshed = true, prefix = "hello", type = YAML)
@ConfigurationProperties( prefix = "hello")
public class PatternProperties {
    private String config;
    private String username;
    private String password;

}
