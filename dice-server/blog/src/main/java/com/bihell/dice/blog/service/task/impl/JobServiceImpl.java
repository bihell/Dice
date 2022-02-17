package com.bihell.dice.blog.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bihell.dice.blog.mapper.tool.TaskMapper;
import com.bihell.dice.blog.model.dto.QuartzJob;
import com.bihell.dice.blog.model.tool.Task;
import com.bihell.dice.framework.common.exception.ErrorCode;
import com.bihell.dice.framework.common.exception.TipException;
import com.bihell.dice.blog.service.task.BaseJob;
import com.bihell.dice.blog.service.task.JobService;
import com.bihell.dice.blog.service.task.NoConurrentBaseJob;
import com.bihell.dice.blog.service.task.TaskUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bihell.dice.framework.util.DateUtil.convertToLocalDateTime;

/**
 * @author haseochen
 */
@Slf4j
@Service
@Transactional(rollbackFor = Throwable.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JobServiceImpl implements JobService {
    private static final Logger logger = LoggerFactory.getLogger(JobService.class);
    private final TaskMapper taskMapper;
    private final Scheduler scheduler;

    /**
     * @return
     */
    @Override
    public List<QuartzJob> getTaskList() {
        List<Task> tasks = taskMapper.selectList(new QueryWrapper<Task>().lambda().eq(Task::getStatus, 1));
        List<QuartzJob> jobs = new ArrayList<>();
        for (Task task : tasks) {
            jobs.add(getJob(task));
        }
        return jobs;
    }

    /**
     * 添加任务
     *
     * @param job
     * @throws SchedulerException
     */
    @Override
    public boolean addJob(QuartzJob job) throws SchedulerException {
        logger.info("新增任务Id：{}, name:{}", job.getJobName(), job.getDescription());
        if (job.getStatus() == 0) {
            return false;
        }
        if (!TaskUtils.isValidExpression(job.getCronExpression())) {
            logger.error("时间表达式错误（" + job.getJobName() + "," + job.getJobGroup() + "）," + job.getCronExpression());
            return false;
        } else {
            // 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 不存在，创建一个
            if (null == trigger) {
                //是否允许并发执行
                Class<? extends Job> clazz = job.isConcurrent() ? BaseJob.class : NoConurrentBaseJob.class;
                JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
                jobDetail.getJobDataMap().put("job", job);
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, trigger);
            } else {     // trigger已存在，则更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        return true;
    }

    @Override
    public QuartzJob getJob(Task task) {
        QuartzJob job = null;
        if (task != null) {
            job = new QuartzJob();
            job.setJobName(String.valueOf(task.getId()));
            job.setJobGroup(task.getJobGroup());
            job.setCronExpression(task.getCron());
            job.setConcurrent(task.isConcurrent());
            job.setJobClass(task.getJobClass());
            job.setDescription(task.getName());
            job.setStatus(task.getStatus());
            if (StringUtils.isNotBlank(task.getData())) {
                try {
                    Map<String, Object> dataMap = JSON.parseObject(task.getData(), Map.class);
                    job.setDataMap(dataMap);
                } catch (Exception e) {
                    throw new TipException(ErrorCode.TASK_CONFIG_ERROR.getMsg());
                }
            }
        }
        return job;
    }

    /**
     * 获取单个任务
     *
     * @param jobName
     * @param jobGroup
     * @return
     */

    @Override
    public QuartzJob getJob(String jobName, String jobGroup) throws SchedulerException {
        QuartzJob job = null;
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (null != trigger) {
            job = new QuartzJob();
            job.setJobName(jobName);
            job.setJobGroup(jobGroup);
            job.setDescription("触发器:" + trigger.getKey());
            job.setNextTime(convertToLocalDateTime(trigger.getNextFireTime()));
            job.setPreviousTime(convertToLocalDateTime(trigger.getPreviousFireTime()));
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            job.setJobStatus(triggerState.name());
            if (trigger instanceof CronTrigger) {
                CronTrigger cronTrigger = (CronTrigger) trigger;
                String cronExpression = cronTrigger.getCronExpression();
                job.setCronExpression(cronExpression);
            }
        }
        return job;
    }

    /**
     * 删除任务
     */
    @Override
    public boolean deleteJob(QuartzJob job) {
        logger.info("删除任务：{}", job.getJobName());
        JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
        try {
            scheduler.deleteJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
