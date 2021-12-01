package com.edu;

import com.edu.config.vo.Demo1Config;
import com.edu.config.vo.Demo2Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Demo1Config.class, Demo2Config.class})
public class BootApplication {


    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class,args);
    }
}
