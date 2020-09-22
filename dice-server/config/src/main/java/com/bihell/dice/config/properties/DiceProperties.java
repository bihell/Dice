package com.bihell.dice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Dice 属性配置信息
 *
 * @author haseochen
 */
@Data
@ConfigurationProperties("dice")
public class DiceProperties {
    /**
     * Authentication enabled
     */
    private boolean authEnabled = true;
}
