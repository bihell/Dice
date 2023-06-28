package com.bihell.dice.service;

import com.bihell.dice.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void resetUser(){
        sysUserService.resetUser("dice","dice","tpxcer@outlook.com");
    }

    @Test
    public void  selectUserByUsername(){
        System.out.println(sysUserService.selectUserByUsername("dice").toString());
    }
}
