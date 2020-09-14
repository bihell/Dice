package com.bihell.dice.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    private final static String USERKEY = "username";
    private final static String AGEKEY = "age";
    private final static String CITYKEY = "city";

    private final static String LISTKEY = "studentList";
    private final static String SETKEY = "usernameSet";
    private final static String HASHKEY = "studentHash";

    /**
     * 添加字符串
     */
    @Test
    public void setString() {
        redisTemplate.opsForValue().set(USERKEY, "nasus");
        redisTemplate.opsForValue().set(AGEKEY, 24);
        redisTemplate.opsForValue().set(CITYKEY, "清远");
    }

}
