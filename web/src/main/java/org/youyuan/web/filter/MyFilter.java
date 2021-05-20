package org.youyuan.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 15:25
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
