package com.bihell.dice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
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
    private Date nextTime;
    private Date previousTime;
    private Integer status;
    private Map<String, Object> dataMap;
}
