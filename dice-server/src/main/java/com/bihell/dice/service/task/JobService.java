package com.bihell.dice.service.task;

import com.bihell.dice.model.dto.QuartzJob;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * @author haseochen
 */
public interface JobService {

    /**
     * @return
     */
    public List<QuartzJob> getTaskList();

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    public boolean addJob(QuartzJob job) throws SchedulerException;

}
