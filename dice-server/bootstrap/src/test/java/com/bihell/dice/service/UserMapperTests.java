package com.bihell.dice.service;

import com.bihell.dice.system.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTests {


    @Resource
    private UserMapper userMapper;

    @Transactional
    @Test
    @Rollback
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "test");
        map.put("password", "test");
        userMapper.insertByMap(map);
    }

}
