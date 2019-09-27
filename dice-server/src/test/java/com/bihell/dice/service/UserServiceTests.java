package com.bihell.dice.service;

import com.bihell.dice.model.params.LoginParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;


    @Test
    public void resetPassword(){
        userService.resetPassword("dice","123456","123456");
    }

    @Test
    public void resetUser(){
        userService.resetUser("dice","dice","tpxcer@outlook.com");
    }
}
