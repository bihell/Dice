package com.bihell.dice.system.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上传拦截器 todo
 */
@Slf4j
@ConditionalOnProperty(value = {"dice.interceptor.upload.enable"}, matchIfMissing = true)
public class UploadInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果访问的不是控制器,则跳出,继续执行下一个拦截器
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 访问路径
        String url = request.getRequestURI();
        // 访问全路径
        String fullUrl = request.getRequestURL().toString();
        // 上传拦截器，业务处理代码
        log.debug("UploadInterceptor...");
        // 访问token，如果需要，可以设置参数，进行鉴权
//        String token = request.getParameter(JwtTokenUtil.getTokenName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 记录实际上传日志...
    }
}
