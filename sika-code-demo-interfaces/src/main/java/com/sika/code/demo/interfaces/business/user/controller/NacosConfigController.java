package com.sika.code.demo.interfaces.business.user.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xk
 * @since 2023.05.07 14:25
 */
@RequestMapping("/config")
@RestController
public class NacosConfigController {

    @Value(value = "${secret}")
    private String secret;
    @Value(value = "${secret1}")
    private String secret1;
    @Value("${nacos.config.data-id}")
    private String dataId;
    @Value("${nacos.config.group}")
    private String group;

    @NacosInjected
    private ConfigService configService;

    @GetMapping("getSecret")
    public String getSecret(){
        return secret;
    }

    // 可以获取nacos同一个命名空间下的其他dataId和group下面的配置
    @GetMapping("getConfig")
    public String getConfig() throws NacosException {
        return configService.getConfig(dataId,group,5000);
    }
}