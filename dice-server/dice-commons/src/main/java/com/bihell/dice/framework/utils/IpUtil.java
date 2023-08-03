package com.bihell.dice.framework.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * IP 地址工具类
 *
 * @author Tang
 */
@Slf4j
@Component
public class IpUtil {
    private static Searcher searcher;

    /**
     * 程序启动时，将ip2region.xdb一次性加载到内存中
     * 并发场景下可安全使用
     */
    @PostConstruct
    private static void init() {
        InputStream inputStream = null;
        try {
            inputStream = new ClassPathResource("ip2region.xdb").getInputStream();
            byte[] buff = FileCopyUtils.copyToByteArray(inputStream);
            searcher = Searcher.newWithBuffer(buff);
            log.info("加载ip2region.xdb成功");
        } catch (IOException e) {
            log.error("加载ip2region.xdb异常");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PreDestroy
    public static void destroy() {
        try {
            if (searcher != null) {
                searcher.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取客户端IP
     *
     * @param request {@link HttpServletRequest}
     * @return IP 地址
     */
    // TODO: 2023/6/28 获取ip单独拆分
    public static String getIpAddr(HttpServletRequest request) {
        var headers = List.of("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP");
        var unknown = "unknown";

        if (Objects.isNull(request)) {
            return unknown;
        }
        var ipAddr = headers.stream()
                .map(request::getHeader)
                .filter(ip -> Objects.nonNull(ip) && ip.length() != 0 && !unknown.equalsIgnoreCase(ip))
                .findFirst()
                .orElse(request.getRemoteAddr());
        return "0:0:0:0:0:0:0:1".equals(ipAddr) ? "127.0.0.1" : ipAddr;
    }

    /**
     * 获取城市名称
     *
     * @param ip IP 地址
     * @return 城市名称
     */
    public static String getCity(String ip) {
        try {
            var region = searcher.search(ip);
            return region.split("\\|")[3];
        } catch (Exception e) {
            log.error("解析IP归属地信息异常：" + ip);
            e.printStackTrace();
            return null;
        }
    }
}
