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
     * Doc api disabled. (Default is true)
     */
    private boolean docDisabled = true;
}
