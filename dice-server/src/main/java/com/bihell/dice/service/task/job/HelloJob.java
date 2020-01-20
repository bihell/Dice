package com.bihell.dice.service.task.job;


import com.alibaba.fastjson.JSON;
import com.bihell.dice.service.task.JobExecuter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * HelloJob
 *
 * @author zt
 * @version 2018/12/30 0030
 */
@Component
public class HelloJob extends JobExecuter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(Map<String, Object> dataMap) throws Exception {

        logger.info("hello :"+ JSON.toJSONString(dataMap));
    }
}
