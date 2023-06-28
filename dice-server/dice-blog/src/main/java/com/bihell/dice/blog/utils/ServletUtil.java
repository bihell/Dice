package com.bihell.dice.blog.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Servlet utilities.
 *
 * @author johnniang
 * @date 19-4-21
 */
public class ServletUtil {

    private ServletUtil() {
    }

    /**
     * Gets current http servlet request.
     *
     * @return an optional http servlet request
     */
    @NonNull
    public static Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest);
    }

    /**
     * Gets request ip.
     *
     * @return ip address or null
     */
    @Nullable
    public static String getRequestIp() {
        return getCurrentRequest().map(cn.hutool.extra.servlet.JakartaServletUtil::getClientIP).orElse(null);
    }

    /**
     * Gets request header.
     *
     * @param header http header name
     * @return http header of null
     */
    @Nullable
    public static String getHeaderIgnoreCase(String header) {
        return getCurrentRequest().map(request -> cn.hutool.extra.servlet.JakartaServletUtil.getHeaderIgnoreCase(request, header)).orElse(null);
    }

}
