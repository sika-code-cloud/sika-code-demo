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
@RequestMapping("/apollo/config")
@RestController
public class ApolloConfigController {

    @Value(value = "${test.value}")
    private String secret;

    @Autowired
    private MetricsProperties metricsProperties;

    @GetMapping("getSecret")
    public String getSecret() {
        return secret;
    }


    @GetMapping("getSecretTemp")
    public Object getSecretTemp() {
        return metricsProperties.getInvoke().getAlertRules();
    }

}