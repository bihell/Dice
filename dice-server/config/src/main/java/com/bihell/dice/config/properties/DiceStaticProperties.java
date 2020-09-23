package com.bihell.dice.config.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
public class DiceStaticProperties {

    public static String INFO_PROJECT_VERSION = "";

    @Value("${info.project-version}")
    private String infoProjectVersion;

    @PostConstruct
    public void init() {
        INFO_PROJECT_VERSION = this.infoProjectVersion;
        log.debug("INFO_PROJECT_VERSION:" + INFO_PROJECT_VERSION);
    }

}
