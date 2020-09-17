package com.bihell.dice.blog.service.task;

import com.bihell.dice.blog.model.dto.QuartzJob;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author haseochen
 */
@Component
public class BaseJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap data = context.getJobDetail().getJobDataMap();
        QuartzJob job = (QuartzJob) data.get("job");
        try {
            TaskUtils.executeJob(job);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
