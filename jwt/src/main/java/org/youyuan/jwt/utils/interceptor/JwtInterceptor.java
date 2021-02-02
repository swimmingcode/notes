package org.youyuan.jwt.utils.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.utils.jwt.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/1/30 22:03
 */
@Component
public class JwtInterceptor  implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        getCurrentToken(request);
        return true;
    }

    private void getCurrentToken(HttpServletRequest request) {
        //获取当前Token
        String token = request.getHeader("Token");
        if (token != null) {
            Token verifyToken = tokenService.verifyToken(token);
            request.setAttribute("Token", verifyToken);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
