package com.bihell.dice.auth.web.service;

import cn.hutool.http.useragent.UserAgentUtil;
import com.bihell.dice.framework.constant.CachePrefix;
import com.bihell.dice.framework.properties.TokenProperties;
import com.bihell.dice.framework.model.UserModel;
import com.bihell.dice.framework.utils.IdUtils;
import com.bihell.dice.framework.utils.IpUtils;
import com.bihell.dice.framework.utils.RedisUtils;
import com.bihell.dice.framework.utils.ServletUtils;
import com.bihell.dice.system.entity.SysUser;
import com.bihell.dice.system.mapper.SysUserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * token 验证处理
 *
 * @author Tang
 */
@Component
public class TokenService {

    private static final String LOGIN_USER_KEY = "login_user_key";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final RedisUtils redisUtils;

    private final TokenProperties tokenProperties;

    private final SysUserMapper userMapper;

    public TokenService(RedisUtils redisUtils, TokenProperties tokenProperties, SysUserMapper userMapper) {
        this.redisUtils = redisUtils;
        this.tokenProperties = tokenProperties;
        this.userMapper = userMapper;
    }

    /**
     * 获取登陆用户身份信息
     *
     * @param request request
     * @return 登陆用户信息
     */
    public UserModel get(@NonNull HttpServletRequest request) {
        // 获取请求携带的令牌
        var token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            var claims = parseToken(token);
            // 解析对应的权限以及登陆用户信息
            var uuid = (String) claims.get(LOGIN_USER_KEY);
            var userKey = getTokenKey(uuid);
            var userModel = redisUtils.get(userKey);
            return (UserModel) userModel;
        }
        return null;
    }

    /**
     * 删除登陆用户身份信息
     *
     * @param token 令牌
     */
    public void delete(String token) {
        if (StringUtils.isNotEmpty(token)) {
            var userKey = getTokenKey(token);
            redisUtils.delete(userKey);
        }
    }

    /**
     * 创建令牌
     *
     * @param userModel 登陆用户信息
     * @return 令牌
     */
    public String createToken(@NonNull UserModel userModel) {
        var token = IdUtils.getUUID();
        userModel.setToken(token);
        setUserAgent(userModel);
        set(userModel);
        var claims = new HashMap<String, Object>(16);
        claims.put(LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 设置登陆用户身份信息
     *
     * @param userModel 登陆用户信息
     */
    public void set(@NonNull UserModel userModel) {
        if (StringUtils.isNotEmpty(userModel.getToken())) {
            refreshToken(userModel);
        }
    }

    /**
     * 验证令牌有效期
     *
     * @param userModel 登陆用户信息
     */
    public void verifyToken(@NonNull UserModel userModel) {
        refreshToken(userModel);
    }

    /**
     * 刷新令牌有效期
     *
     * @param userModel 登陆用户信息
     */
    public void refreshToken(@NonNull UserModel userModel) {
        userModel.setExpireTime(LocalDateTime.now().plus(Duration.ofMillis(tokenProperties.getExpireTimeLong())));
        // 缓存登陆信息
        var userKey = getTokenKey(userModel.getToken());
        redisUtils.set(userKey, userModel, tokenProperties.getExpireTime(), tokenProperties.getTimeUnit());
    }

    /**
     * 设置登陆用户代理信息
     *
     * @param userModel 登陆用户信息
     */
    public void setUserAgent(@NonNull UserModel userModel) {
        var userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        userModel.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        userModel.setLocation(IpUtils.getCity(userModel.getIp()));
        userModel.setMobile(userAgent.isMobile());
        userModel.setBrowser(userAgent.getBrowser().getName());
        userModel.setVersion(userAgent.getVersion());
        userModel.setPlatform(userAgent.getPlatform().getName());
        userModel.setOs(userAgent.getOs().getName());
        userModel.setOsVersion(userAgent.getOsVersion());
        userModel.setEngine(userAgent.getEngine().getName());
        userModel.setEngineVersion(userAgent.getEngineVersion());
        userModel.setLoginTime(LocalDateTime.now());
        updateUserLoginInfo(userModel);
    }

    /**
     * 获取登陆用户代理信息
     *
     * @return 登陆用户信息
     */
    public UserModel getUserAgent() {
        var userModel = new UserModel();
        var userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        userModel.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        userModel.setLocation(IpUtils.getCity(userModel.getIp()));
        userModel.setMobile(userAgent.isMobile());
        userModel.setBrowser(userAgent.getBrowser().getName());
        userModel.setVersion(userAgent.getVersion());
        userModel.setPlatform(userAgent.getPlatform().getName());
        userModel.setOs(userAgent.getOs().getName());
        userModel.setOsVersion(userAgent.getOsVersion());
        userModel.setEngine(userAgent.getEngine().getName());
        userModel.setEngineVersion(userAgent.getEngineVersion());
        userModel.setLoginTime(LocalDateTime.now());
        return userModel;
    }

    /**
     * 更新用户登陆信息
     *
     * @param userModel 登陆用户信息
     */
    private void updateUserLoginInfo(@NonNull UserModel userModel) {
        var user = new SysUser();
        user.setId(userModel.getUser().getUserId());
        user.setLoginIp(userModel.getIp());
        user.setLoginDate(userModel.getLoginTime());
        userMapper.updateById(user);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
            .setClaims(claims)
            .signWith(getSecretKey())
            .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 生成密钥
     *
     * @return 密钥
     */
    private SecretKey getSecretKey() {
        var secretBytes = Decoders.BASE64.decode(tokenProperties.getSecret());
        return Keys.hmacShaKeyFor(secretBytes);
    }

    /**
     * 获取请求token
     *
     * @param request request
     * @return 令牌
     */
    private String getToken(@NonNull HttpServletRequest request) {
        var token = request.getHeader(tokenProperties.getHeader());
        if (StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)) {
            token = token.replace(TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 获取 redis 键
     *
     * @param uuid 唯一标识
     * @return 键
     */
    private String getTokenKey(String uuid) {
        return CachePrefix.LOGIN_TOKENS + uuid;
    }

}
