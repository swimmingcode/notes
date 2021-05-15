//package org.youyuan.web;
//
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * @Describe: #请描述当前类的功能#
// * @Author: youjiancheng
// * @date 2021/4/28 11:34
// */
//@Component
//public class Filter implements javax.servlet.Filter {
//
//    /**
//     * 该方法在容器启动初始化过滤器时被调用，它在 Filter 的整个生命周期只会被调用一次。
//     * 注意：这个方法必须执行成功，否则过滤器会不起作用。
//     *
//     * @param filterConfig
//     * @throws ServletException
//     */
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("init");
//    }
//
//    /**
//     * 容器中的每一次请求都会调用该方法， FilterChain 用来调用下一个过滤器 Filter。
//     *
//     * @param request
//     * @param response
//     * @param chain
//     * @throws IOException
//     * @throws ServletException
//     */
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("doFilter");
//        chain.doFilter(request,response);
//    }
//
//    /**
//     *  当容器销毁 过滤器实例时调用该方法，一般在方法中销毁或关闭资源，在过滤器 Filter 的整个生命周期也只会被调用一次
//     */
//    @Override
//    public void destroy() {
//        System.out.println("destroy");
//    }
//}
