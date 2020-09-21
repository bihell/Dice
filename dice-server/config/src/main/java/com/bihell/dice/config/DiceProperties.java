package com.bihell.dice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Dice configuration properties.
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
