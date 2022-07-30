package com.sika.code.demo.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 启动类
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}非常重要，如果没有关闭，在启动程序时会发生循环依赖的错误
 *
 * @author daiqi
 * @create 2019-05-10 21:16
 */
@SpringBootApplication(scanBasePackages = {"com.sika.code.demo", "cn.hutool.extra.spring"})
@MapperScan({"com.sika.code.demo.**.mapper"})
@Slf4j
public class SikaCOdeDemoApplication {
    //    static {
//        System.setProperty("log4j2.isThreadContextMapInherimeble", "true");
//        System.setProperty("dubbo.application.logger", "log4j2");
//    }
    public static void main(String[] args) {
        SpringApplication.run(SikaCOdeDemoApplication.class, args);

    }

}
