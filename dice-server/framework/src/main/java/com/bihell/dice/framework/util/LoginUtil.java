package com.bihell.dice.framework.util;

import com.bihell.dice.config.constant.CommonRedisKey;
import com.bihell.dice.framework.shiro.util.JwtTokenUtil;
import com.bihell.dice.framework.shiro.util.JwtUtil;
import com.bihell.dice.framework.shiro.vo.LoginSysUserRedisVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * 获取登录信息工具类
 */
@Slf4j
@Component
public class LoginUtil {

    private static RedisTemplate redisTemplate;

    public LoginUtil(RedisTemplate redisTemplate) {
        LoginUtil.redisTemplate = redisTemplate;
    }



    /**
     * 通过token获取用户对象
     *
     * @return
     */
    public static LoginSysUserRedisVo getLoginSysUserRedisVo(String token) {
        // 获取当前登录用户
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return (LoginSysUserRedisVo) redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_USER, username));
    }
    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public static LoginSysUserRedisVo getLoginSysUserRedisVo() {
        // 获取当前登录用户
        String token = JwtTokenUtil.getToken();
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return (LoginSysUserRedisVo) redisTemplate.opsForValue().get(String.format(CommonRedisKey.LOGIN_USER, username));
    }

    /**
     * 获取当前登录用户的ID
     *
     * @return
     */
    public static Long getUserId() {
        LoginSysUserRedisVo loginSysUserRedisVo = getLoginSysUserRedisVo();
        if (loginSysUserRedisVo == null) {
            return null;
        }
        return loginSysUserRedisVo.getId();
    }

    /**
     * 根据token获取用户的ID
     *
     * @return
     */
    public static Long getUserId(String token) {
        LoginSysUserRedisVo loginSysUserRedisVo = getLoginSysUserRedisVo(token);
        if (loginSysUserRedisVo == null) {
            return null;
        }
        return loginSysUserRedisVo.getId();
    }

    /**
     * 获取当前登录用户的账号
     *
     * @return
     */
    public static String getUsername() {
        LoginSysUserRedisVo loginSysUserRedisVo = getLoginSysUserRedisVo();
        if (loginSysUserRedisVo == null) {
            return null;
        }
        return loginSysUserRedisVo.getUsername();
    }

}
