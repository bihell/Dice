package com.bihell.dice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Spring boot
 *
 * @author bihell
 * @since 2017/7/5.
 */
@EnableAsync
@EnableCaching
@EnableTransactionManagement
@MapperScan("com.bihell.dice.**.mapper")
@EnableScheduling
@EnableConfigurationProperties
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.bihell.dice"})
public class DiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiceApplication.class, args);
    }
}
