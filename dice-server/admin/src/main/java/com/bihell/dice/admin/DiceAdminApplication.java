package com.bihell.dice.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Spring Boot Admin 启动类
 *
 * @author geekidea
 * @date 2020/3/20
 **/
@Slf4j
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
public class DiceAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DiceAdminApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        String serverPort = environment.getProperty("server.port");
        log.info("SpringBootAdmin: http://localhost:" + serverPort);
    }

}
