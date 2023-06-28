package com.bihell.dice.framework.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * token 配置属性
 *
 * @author Tang
 */
@Configuration
@ConfigurationProperties(TokenProperties.TOKEN_PREFIX)
public class TokenProperties {

    public static final String TOKEN_PREFIX = "token";

    /**
     * 令牌自定义标识
     */
    private String header;

    /**
     * 令牌秘钥
     */
    private String secret;

    /**
     * 时间颗粒度
     */
    private TimeUnit timeUnit;

    /**
     * 令牌有效期
     */
    private int expireTime;


    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public long getExpireTimeLong() {
        return TimeUnit.MILLISECONDS.convert(this.getExpireTime(), this.getTimeUnit());
    }

}
