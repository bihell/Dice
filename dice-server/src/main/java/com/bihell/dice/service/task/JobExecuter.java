package com.bihell.dice.service.task;

import com.bihell.dice.model.dto.QuartzJob;
import com.bihell.dice.model.tool.Task;
import com.bihell.dice.model.tool.TaskLog;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public abstract class JobExecuter {

    protected static final Logger log = LoggerFactory.getLogger(JobExecuter.class);

    private QuartzJob job;

    public void setJob(QuartzJob job) {
        this.job = job;
    }

    public void execute() {
        Map dataMap = job.getDataMap();
        String taskId = job.getJobName();
        Task task = new Task().selectById(Long.valueOf(taskId));
        final String taskName = task.getName();
        log.info(">>>>>>>>>>>>>>>>>开始执行定时任务[" + taskName + "]...<<<<<<<<<<<<<<<<<<<");

        String exeResult = "执行成功";
        TaskLog taskLog = new TaskLog();
        taskLog.setName(taskName);
        Date exeAt = new Date();
        taskLog.setExecAt(exeAt);
        taskLog.setIdTask(task.getId());
        //默认是成功 出异常后改成失败
        taskLog.setExecSuccess(1);

        try {
            execute(dataMap);
        } catch (Exception e) {
            log.error("exeucte " + getClass().getName() + " error : ", e);
            exeResult = "执行失败\n";
            exeResult += ExceptionUtils.getStackTrace(e);
            taskLog.setExecSuccess(0);
            taskLog.setJobException(e.getClass().getName());
        }
        task.setExecResult(exeResult);
        task.setExecAt(exeAt);
        taskLog.insert();
        task.updateById();
        log.info(">>>>>>>>>>>>>>>>>执行定时任务[" + taskName + "]结束<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * @param dataMap 数据库配置的参数
     */
    public abstract void execute(Map<String, Object> dataMap) throws Exception;

}
