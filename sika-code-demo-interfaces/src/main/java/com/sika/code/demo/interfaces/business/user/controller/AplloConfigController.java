package com.sika.code.demo.interfaces.business.user.controller;

import com.sika.code.monitor.core.common.properties.MetricsProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AplloConfigController {

    @Value(value = "${secret}")
    private String secret;
    @Autowired
    private PatternProperties patternProperties;
    @Value(value = "${secret1}")
    private String secret1;
    //    @Value("${nacos.config.data-id}")
    private String dataId;
    //    @Value("${nacos.config.group}")
    private String group;

    @Autowired
    private MetricsProperties metricsProperties;

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

}