package com.bihell.dice.interceptor;

import com.bihell.dice.security.SecurityUtil;
import com.bihell.dice.security.UserDetail;
import com.bihell.dice.security.authentication.AuthenticationImpl;
import com.bihell.dice.security.context.SecurityContextHolder;
import com.bihell.dice.security.context.SecurityContextImpl;
import com.bihell.dice.service.RedisService;
import com.bihell.dice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bihell.dice.model.domain.User;
import com.bihell.dice.utils.ErrorCode;
import com.bihell.dice.utils.DiceUtil;
import com.bihell.dice.utils.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.bihell.dice.utils.DiceConsts.ADMIN_TOKEN_QUERY_NAME;

/**
 * 管理后台 拦截器
 *
 * @author bihell
 * @since 2017/10/11 14:10
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminInterceptor implements HandlerInterceptor {

    private static final String AUTH_URIS = "/v1/api/admin";

    private static final String[] IGNORE_URIS = {"/v1/api/admin/auth/login"};

    private final static String ALLOW_HEADERS = org.apache.commons.lang3.StringUtils.joinWith(",", HttpHeaders.CONTENT_TYPE, HttpHeaders.AUTHORIZATION);

    private final UserService userService;

    private final RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        String ip = DiceUtil.getIp();

        log.info("用户访问地址: {}, Http类型: {}, ip地址: {}", url, request.getMethod(), ip);

        if (url.contains(AUTH_URIS)) {
            boolean auth = true;
            //登录拦截忽略url
            for (String param : IGNORE_URIS) {
                if (StringUtils.endsWithIgnoreCase(url, param)) {
                    auth = false;
                }
            }
            //登录拦截
            if (auth) {

                if (request.getHeader(HttpHeaders.ORIGIN) != null) {
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, PUT, DELETE");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "86400");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "x-requested-with");
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALLOW_HEADERS);
                }

                // Get token from request
                String token = getTokenFromRequest(request);

                if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
                    PrintWriter out = response.getWriter();
                    ObjectMapper mapper = new ObjectMapper();
                    out.print(mapper.writeValueAsString(RestResponse.fail(ErrorCode.NOT_LOGIN.getCode(), ErrorCode.NOT_LOGIN.getMsg())));
                    out.flush();
                    return false;
                }

                // Get user id from cache
                Integer userId = (Integer) redisService.get(SecurityUtil.buildTokenAccessKey(token));

                if (userId == null) {
                    PrintWriter out = response.getWriter();
                    ObjectMapper mapper = new ObjectMapper();
                    out.print(mapper.writeValueAsString(RestResponse.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg())));
                    out.flush();
                    return false;
                }

                // Get the user
                User user = userService.getById(userId);
                user.setPasswordMd5("");

                // Build user detail
                UserDetail userDetail = new UserDetail(user);

                // Set security
                SecurityContextHolder.setContext(new SecurityContextImpl(new AuthenticationImpl(userDetail)));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }

    private String getTokenFromRequest(@NonNull HttpServletRequest request) {
        Assert.notNull(request, "Http servlet request must not be null");

        // Get from header
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Get from param
        if (org.apache.commons.lang3.StringUtils.isBlank(token)) {
            token = request.getParameter(ADMIN_TOKEN_QUERY_NAME);

            log.debug("Got token from parameter: [{}: {}]", ADMIN_TOKEN_QUERY_NAME, token);
        } else {
            log.debug("Got token from header: [{}: {}]", HttpHeaders.AUTHORIZATION, token);
        }

        return token;
    }
}
