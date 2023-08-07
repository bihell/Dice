package com.bihell.dice.blog.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @author haseochen
 */
@Getter
@Setter
public class QuartzJob implements Serializable {
    private String jobId;
    private String jobName;
    private String jobGroup;
    private String jobClass;
    private String description;
    private String cronExpression;
    private boolean concurrent;
    private String jobStatus;
    private LocalDateTime nextTime;
    private LocalDateTime previousTime;
    private Integer status;
    private Map<String, Object> dataMap;
}
