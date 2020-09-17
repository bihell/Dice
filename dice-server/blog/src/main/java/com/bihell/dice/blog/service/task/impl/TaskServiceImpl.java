package com.bihell.dice.blog.service.task.impl;

import com.bihell.dice.blog.model.dto.QuartzJob;
import com.bihell.dice.blog.model.tool.Task;
import com.bihell.dice.blog.service.task.JobService;
import com.bihell.dice.blog.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final JobService jobService;

    /**
     * @param task
     * @return
     */
    @Override
    public Task save(Task task) {
        logger.info("新增定时任务%s", task.getName());
        task.insert();
        try {
            jobService.addJob(jobService.getJob(task));
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return task;
    }

    @Override
    public Task disable(Long id) {
        Task task = new Task().selectById(id);
        task.setStatus(0);
        task.updateById();
        logger.info("禁用定时任务{}", id.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return task;
    }

    @Override
    public Task enable(Long id) {
        Task task = new Task().selectById(id);
        task.setStatus(1);
        task.updateById();
        logger.info("启用定时任务{}", id.toString());
        try {
            QuartzJob job = jobService.getJob(task.getId().toString(), task.getJobGroup());
            if (job != null) {
                jobService.deleteJob(job);
            }
            if (task.getStatus() == 1) {
                jobService.addJob(jobService.getJob(task));
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public void delete(Long id) {
        Task task = new Task().selectById(id);
        task.deleteById();
        try {
            logger.info("删除定时任务{}", id.toString());
            QuartzJob job = jobService.getJob(task);
            if (job != null) {
                jobService.deleteJob(job);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
