package com.bihell.dice.framework.utils;

import com.bihell.dice.framework.constant.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Servlet 工具类
 *
 * @author Tang
 */
public class ServletUtils {

    private ServletUtils() {
    }

    /**
     * 获取 {@link ServletRequestAttributes}
     *
     * @return {@link ServletRequestAttributes}
     */
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    }

    /**
     * 获取 {@link HttpServletRequest}
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取 {@link HttpServletResponse}
     *
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取 {@link HttpSession}
     *
     * @return {@link HttpSession}
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取 {@link String} 参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(HttpStatus.SUCCESS);
            response.setContentType("application/json");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
