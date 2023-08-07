package com.bihell.dice.commons.utils;

import com.bihell.dice.commons.constant.LoginConstant;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


/**
 * Token工具类
 *
 * @author geekidea
 * @date 2022-1-05
 **/
@Slf4j
@Component
public class TokenUtil {

    private static String TOKEN_PREFIX = "boot.auth.";

    /**
     * 生成 Token
     *
     * @return
     */
    public static String generateToken() throws Exception {
        // 生成token
        String token = TOKEN_PREFIX + UUIDUtil.getUuid();
        return token;
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() throws Exception {
        return getToken(HttpRequestUtil.getRequest());
    }

    /**
     * 从请求头或者请求参数中获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) throws Exception {
        if (request == null) {
            throw new IllegalArgumentException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(LoginConstant.TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // 从请求参数中获取token
            token = request.getParameter(LoginConstant.TOKEN_NAME);
        }
        if (StringUtils.isBlank(token)) {
            // 从cookie中获取token
            token = getTokenByCookie(request);
            if (StringUtils.isNotBlank(token) && !token.startsWith(TOKEN_PREFIX)) {
                token = null;
            }
        }
        if (StringUtils.isBlank(token)) {
            return null;
        }
        // 校验token
        if (!token.startsWith(TOKEN_PREFIX)) {
            log.error("token错误:" + token);
            // TODO: 2023/7/11
//            throw new LoginTokenException("token错误");
        }
        return token;
    }

    /**
     * 从Cookie中获取token
     *
     * @param request
     * @return
     */
    public static String getTokenByCookie(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (ArrayUtils.isEmpty(cookies)) {
            return null;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if (LoginConstant.TOKEN_NAME.equals(name)) {
                String value = cookie.getValue();
                return value;
            }
        }
        return null;
    }

}
