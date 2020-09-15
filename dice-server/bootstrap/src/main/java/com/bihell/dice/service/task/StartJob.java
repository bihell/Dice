package com.bihell.dice.service.task;

//import cn.enilu.flash.bean.vo.QuartzJob;
//import cn.enilu.flash.service.task.JobService;
import com.bihell.dice.model.dto.QuartzJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化定时任务
 *
 * @author enilu
 * @Date 2019-08-13
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StartJob implements ApplicationRunner {

    private final JobService jobService;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("任务调度启动。。。");
        List<QuartzJob> list = jobService.getTaskList();
        for (QuartzJob quartzJob : list) {
            jobService.addJob(quartzJob);
        }
    }
}
