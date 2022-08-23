package com.sika.code.demo.interfaces.common.batch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.sika.code.**.mapper")
@Configuration
public class MyBatisPlusConfig {
  @Bean
  public CustomizedSqlInjector customizedSqlInjector() {
    return new CustomizedSqlInjector();
  }
}
