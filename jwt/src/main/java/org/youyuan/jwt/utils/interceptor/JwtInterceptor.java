package org.youyuan.jwt.utils.interceptor;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.annotation.UnLogin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/1/30 22:03
 */
@Component
public class JwtInterceptor  implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    /**
     * 放行白名单
     */
    String[] whiteList = {
            "/swagger-ui.html",
            "/error",
            "/csrf",
            "/",
            "/favicon.ico",
            "/swagger-resources/**",
            "/null/swagger-resources/configuration/ui",
            "/webjars/springfox-swagger-ui/**"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //白名单放行
        if (whitelistRelease(request)) {
            return true;
        }
        //对不需要鉴权的接口放行
        if (verifyAnnotation(handler)) {
            return true;
        }
        //Token验证
        getCurrentToken(request);
        return true;
    }

    /**
     * 对不需要鉴权的接口放行
     *
     * @param handler
     * @return
     */
    private Boolean verifyAnnotation(Object handler) {
        HandlerMethod hand = (HandlerMethod) handler;
        Method method = hand.getMethod();
        UnLogin unLogin = method.getAnnotation(UnLogin.class);
        if (Optional.ofNullable(unLogin).isPresent()) {
            return true;
        }
        return false;
    }

    /**
     * 白名单放行
     * @param request
     */
    private Boolean whitelistRelease(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String list : whiteList) {
            if (antPathMatcher.match(list,requestURI)) {
                return true;
            }
        }
        return false;
    }



    private void getCurrentToken(HttpServletRequest request) {
        Map<String, Cookie> stringCookieMap = ReadCookieMap(request);
        if (!stringCookieMap.containsKey("token")) {
            throw new ExceptionFactory(ResponseCode.NO_TOKEN);
        }
        //验证token
        tokenService.verifyToken(stringCookieMap.get("token").getValue());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if( null != cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
