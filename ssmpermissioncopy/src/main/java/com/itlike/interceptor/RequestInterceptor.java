package com.itlike.interceptor;

import com.itlike.utils.RequestUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //System.out.println("来到了拦截器");
        RequestUtil.setRequest(request);
        return true;
    }
}
