package com.bihell.dice.commons.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * <code>
 * 浏览器工具类<br/>
 * 1.获取当前浏览器名称
 * 2.判断当前用户的浏览器
 * </code>
 */
public final class BrowserUtil {
    public static final String IE = "msie";
    public static final String FIREFOX = "firefox";
    public static final String CHROME = "chrome";

    private BrowserUtil() {
        throw new AssertionError();
    }

    /**
     * 获取当前浏览器名称
     *
     * @param request
     * @return 返回浏览器名称
     */
    public static String getCurrent(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (userAgent != null && !("".equals(userAgent.trim()))) {
            if (userAgent.indexOf(CHROME) >= 0) {
                return CHROME;
            } else if (userAgent.indexOf(FIREFOX) >= 0) {
                return FIREFOX;
            } else if (userAgent.indexOf(IE) >= 0) {
                return IE;
            }
        }
        return null;
    }

    /**
     * 是否是IE浏览器
     *
     * @param request
     * @return
     */
    public static boolean isIe(HttpServletRequest request) {
        return IE.equals(getCurrent(request));
    }

    /**
     * 是否是Firefox浏览器
     *
     * @param request
     * @return
     */
    public static boolean isFirefox(HttpServletRequest request) {
        return FIREFOX.equals(getCurrent(request));
    }

    /**
     * 是否是Chrome浏览器
     *
     * @param request
     * @return
     */
    public static boolean isChrome(HttpServletRequest request) {
        return CHROME.equals(getCurrent(request));
    }
}
