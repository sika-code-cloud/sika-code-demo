package com.sika.code.demo.interfaces.business.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Executor;

@Component
public class NacosRefresherHandler implements InitializingBean, ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(NacosRefresherHandler.class);
    static final String DATA_ID = "application.yaml";

    static final String GROUP = "DEFAULT_GROUP";

    private final ConfigService configService;
    String yamlString = "person:\n  name: John Doe\n  age: 30";
    private MetricsProperties metricsProperties;

    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    public NacosRefresherHandler() {
        configService = new NacosConfigManager(SpringUtil.getBean(NacosConfigProperties.class)).getConfigService();
        metricsProperties = SpringUtil.getBean(MetricsProperties.class);
    }

    public void initRegisterListener() throws NacosException {
        log.info("初始化监听器了了了了了了");
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                try {
                    YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
                    yamlPropertiesFactoryBean.setResources(new ByteArrayResource(configInfo.getBytes()));
                    Map<Object, Object> map = yamlPropertiesFactoryBean.getObject();
                    ConfigurationPropertySource sources = new MapConfigurationPropertySource(map);
                    Binder binder = new Binder(sources);
                    MetricsProperties properties = binder.bind("management.metrics.sika", MetricsProperties.class).get();

                    BeanUtil.copyProperties(properties, metricsProperties);
                    metricsProperties.getInvoke().getItem().get("dbClient").getAlertRules().get(0).setThreshold(
                            properties.getInvoke().getItem().get("dbClient").getAlertRules().get(0).getThreshold());
                    log.info("before-properties：{}, after-properties:{}", metricsProperties.getInvoke().getItem().get("dbClient").getAlertRules().get(0), properties.getInvoke().getItem().get("dbClient").getAlertRules().get(0));
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        });

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        initRegisterListener();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}