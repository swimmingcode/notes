package com.southwind.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Describe:异常处理
 * @Author: youjiancheng
 * @date 2021/6/7 13:49
 */
public class ExceptionHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        String msg = null;
        if(ex instanceof FlowException) {
            msg = "限流";
        } else if(ex instanceof DegradeException) {
            msg = "降级";
        }
        response.getWriter().write(msg);
    }
}
