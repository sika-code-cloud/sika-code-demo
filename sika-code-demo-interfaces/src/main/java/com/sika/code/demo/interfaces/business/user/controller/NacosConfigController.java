package com.sika.code.demo.interfaces.business.user.controller;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author xk
 * @since 2023.05.07 14:25
 */
@RequestMapping("/nacos/config")
@RestController
public class NacosConfigController {

    @Value(value = "${secret}")
    private String secret;
    @Value(value = "${secret1}")
    private String secret1;
    //    @Value("${nacos.config.data-id}")
    private String dataId;
    //    @Value("${nacos.config.group}")
    private String group;

    @Autowired(required = false)
    private MetricsProperties metricsProperties;

    @Autowired(required = false)
    private NacosConfigProperties nacosConfigProperties;

    @GetMapping("getSecret")
    public String getSecret() {
        return secret;
    }

    @GetMapping("getSecret1")
    public String getSecret1() {
        return secret1;
    }

    @GetMapping("getSecretTemp")
    public Object getSecretTemp() {
        return metricsProperties.getInvoke().getAlertRules();
    }

    // 可以获取nacos同一个命名空间下的其他dataId和group下面的配置
    @GetMapping("getConfig")
    public String getConfig() {
        try {
            return getConfigService().getConfig("test1.yaml", group, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    private ConfigService getConfigService() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, nacosConfigProperties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, nacosConfigProperties.getPassword());
        ConfigService configService;
        try {
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            throw new RuntimeException("Nacos config 配置 异常{}", e);
        }
        return configService;
    }
}