package com.bihell.dice.service;

import com.bihell.dice.blog.service.system.LogService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogServiceTests {
    @Autowired
    LogService logService;

    @Test
    public void getLogs(){
        logService.getLogs(1,2);
    }
}
