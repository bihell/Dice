package com.bihell.dice.listener;

import com.bihell.dice.config.DiceProperties;
import com.bihell.dice.service.blog.OptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * The method executed after the application is started.
 *
 * @author haseochen
 */
@Slf4j
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final OptionService optionService;

    private final DiceProperties diceProperties;

    @Override
    public void onApplicationEvent(@NotNull ApplicationStartedEvent event) {
        // save halo version to database
        this.printStartInfo();
    }

    private void printStartInfo() {
        String baseUrl = optionService.getBaseUrl();

        if (!diceProperties.isDocDisabled()) {
            log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Dice api doc was enabled at ",baseUrl,"/swagger-ui.html"));
        }
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "Dice has started successfully!"));
    }

}
