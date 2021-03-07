package org.youyuan.jwt.utils.common.web;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalHttpUtils {


    static public HttpServletRequest getGlobalHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        //从session里面获取对应的值
        String str = (String) requestAttributes.getAttribute("name",RequestAttributes.SCOPE_SESSION);
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        return request;
    }


    static public HttpServletResponse getGlobalHttpResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        //从session里面获取对应的值
        String str = (String) requestAttributes.getAttribute("name",RequestAttributes.SCOPE_SESSION);
        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        return response;
    }


}
