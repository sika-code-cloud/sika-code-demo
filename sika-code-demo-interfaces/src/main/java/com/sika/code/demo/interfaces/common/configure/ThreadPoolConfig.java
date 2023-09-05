package com.sika.code.demo.interfaces.common.configure;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import com.sika.code.monitor.core.thread.hippo4j.rejected.Hippo4jMonitorAbortPolicyRejectedExecutionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageConsumeDynamicExecutor() {
        String threadPoolId = "message-consume";
        ThreadPoolExecutor messageConsumeDynamicExecutor = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .dynamicPool()
                .build();
        messageConsumeDynamicExecutor.setRejectedExecutionHandler(new Hippo4jMonitorAbortPolicyRejectedExecutionHandler.CustomAbortPolicy());
        return messageConsumeDynamicExecutor;
    }

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageProduceDynamicExecutor() {
        String threadPoolId = "message-produce";
        ThreadPoolExecutor messageProduceDynamicExecutor = ThreadPoolBuilder.builder()
                .threadFactory(threadPoolId)
                .threadPoolId(threadPoolId)
                .dynamicPool()
                .build();
        return messageProduceDynamicExecutor;
    }

}