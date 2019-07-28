package com.bihell.dice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptionServiceTests {
    @Autowired
    OptionService optionService;

    @Test
    public void getAllOptionMap(){
        System.out.println(optionService.getAllOptionMap());
    }

    @Test
    public void get(){
        System.out.println(optionService.get("dice_init",false));
        System.out.println(optionService.get("dice_init"));
    }

    @Test
    public void save(){
        optionService.save("dice_init","true");
        optionService.save("test","false");
    }
}
