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
import com.sika.code.monitor.core.alert.matcher.AlertMatcher;
import com.sika.code.monitor.core.common.config.BaseMetricsConfig;
import com.sika.code.monitor.core.common.config.BaseMetricsItemConfig;
import com.sika.code.monitor.core.common.manager.LoadMetricsConfigManager;
import com.sika.code.monitor.core.common.properties.MetricsProperties;
import com.sika.code.monitor.core.invoke.config.InvokeAlertRuleConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsConfig;
import com.sika.code.monitor.core.invoke.config.InvokeTimedMetricsItemConfig;
import com.sika.code.monitor.core.invoke.metics.InvokeTimedMetrics;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@Component
public class NacosRefresherHandler implements InitializingBean, ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(NacosRefresherHandler.class);
    static final String DATA_ID = "application.yaml";

    static final String GROUP = "DEFAULT_GROUP";

    private final ConfigService configService;
    String yamlString = "person:\n  name: John Doe\n  age: 30";
    private MetricsProperties metricsProperties;
    @Autowired
    private LoadMetricsConfigManager loadMetricsConfigManager;
    @Autowired
    private MeterRegistry meterRegistry;

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
                    BaseMetricsConfig<?> cache = loadMetricsConfigManager.getMetricConfigInstance(InvokeTimedMetricsConfig.class);
                    BaseMetricsConfig<?> propertiesConfig = properties.getConfigByType(InvokeTimedMetricsConfig.class);
                    log.info("cache-config：{}, after-propertiesConfig:{}", cache, propertiesConfig);
                    Map<InvokeTimedMetrics.ID, InvokeTimedMetricsItemConfig> idInvokeTimedMetricsItemConfigMap = InvokeTimedMetrics.idInvokeAlertRuleConfigMap;
                    for (Map.Entry<InvokeTimedMetrics.ID, InvokeTimedMetricsItemConfig> entry : idInvokeTimedMetricsItemConfigMap.entrySet()) {
                        InvokeTimedMetrics.ID id = entry.getKey();
                        List<String> values = id.getTasValues();
                        InvokeTimedMetricsItemConfig config = entry.getValue();
                        InvokeTimedMetricsItemConfig configNew = (InvokeTimedMetricsItemConfig) loadMetricsConfigManager.getMetricsItemConfigInstance(properties, config.getMetricsType(), InvokeTimedMetricsConfig.class);
                        Meter.Id id1 = new Meter.Id(id.getName(), id.getTags(), null, null, Meter.Type.GAUGE);
                        InvokeAlertRuleConfig alertRuleConfigNew = AlertMatcher.matchInvokedTime(configNew, values);
                        meterRegistry.remove(id1);
                        Gauge.builder(configNew.getMetricsName() + ".alert", alertRuleConfigNew, InvokeAlertRuleConfig :: toMillis)
                                .tags(id.getTags())
                                .register(meterRegistry);
                    }
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