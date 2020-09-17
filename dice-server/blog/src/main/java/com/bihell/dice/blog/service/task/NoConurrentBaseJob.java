package com.bihell.dice.blog.service.task;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.stereotype.Component;

/**
 * @author haseochen
 */
@Component
@DisallowConcurrentExecution
public class NoConurrentBaseJob extends BaseJob {
}
