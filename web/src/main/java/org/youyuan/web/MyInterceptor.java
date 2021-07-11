package org.youyuan.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/28 11:44
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 这个方法将在请求处理之前进行调用。
     * 注意：如果该方法的返回值为false ，将视为当前请求结束，不仅自身的拦截器会失效，还会导致其他的拦截器也不再执行。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor 前置");
        return true;
    }

    /**
     * 只有在 preHandle() 方法返回值为true 时才会执行。
     * 会在Controller 中的方法调用之后，DispatcherServlet 返回渲染视图之前被调用。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor 处理中");
    }

    /**
     * 只有在 preHandle() 方法返回值为true 时才会执行。
     * 在整个请求结束之后， DispatcherServlet 渲染了对应的视图之后执行。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor 后置");
    }
}