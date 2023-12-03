package com.spongzi.subject.application.interceptor;

import com.spongzi.subject.application.context.LoginContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author spong
 * @date 2023/12/03
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginId = request.getHeader("loginId");
        LoginContextHolder.set("loginId", loginId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginContextHolder.remove();
    }
}
