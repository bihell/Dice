package com.bihell.dice.service;

import com.bihell.dice.nav.service.NavTypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NavTypeServiceTests {
    @Autowired
    NavTypeService navTypeService;

//    @Test
//    public void getAllNavTypeList(){
//        System.out.println(navTypeService.getAllNavTypeList());
//    }

//    @Test
//    public void getNavTypeTree(){
//        System.out.println(navTypeService.getNavTypeTree());
//    }
}
