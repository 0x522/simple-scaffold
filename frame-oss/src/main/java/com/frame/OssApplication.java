package com.frame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * oss boot
 */
@ComponentScan("com.frame")
@SpringBootApplication
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}
