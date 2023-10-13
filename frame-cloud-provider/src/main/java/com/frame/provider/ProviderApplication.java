package com.frame.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chenyuntao
 */
@SpringBootApplication
@MapperScan(value = "com.frame.*.mapper")
@ComponentScan(value = "com.frame")
@ComponentScan(value = "org.springframework.boot.autoconfigure.data.redis")
@EnableCaching
@EnableScheduling
@EnableFeignClients
//@EnableHystrix
public class ProviderApplication {
    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(ProviderApplication.class);
    }
}
