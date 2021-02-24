package org.youyuan.jwt.utils.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.youyuan.jwt.domain.RolePO;
import org.youyuan.jwt.mapper.UserMapper;
import org.youyuan.jwt.service.TokenService;
import org.youyuan.jwt.service.UserService;
import org.youyuan.jwt.utils.common.redis.RedisUtils;
import org.youyuan.jwt.utils.common.response.ResponseCode;
import org.youyuan.jwt.utils.common.web.GlobalHttpUtils;
import org.youyuan.jwt.utils.exception.ExceptionFactory;
import org.youyuan.jwt.utils.jwt.Token;
import org.youyuan.jwt.utils.jwt.annotation.AccessPermission;
import org.youyuan.jwt.utils.jwt.annotation.UnLogin;
import org.youyuan.jwt.vo.response.Role;
import org.youyuan.jwt.vo.response.UserInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.*;

import static org.youyuan.jwt.utils.common.Constant.BLACK_TOKEN__LIST;
import static org.youyuan.jwt.utils.common.Constant.TOKEN;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/1/30 22:03
 */
@Component
@Slf4j
@Primary
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    public static final String ADMIN = "admin";

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
        //Toke黑名单验证
        verifyBlackTokenList(request);
        //Token验证
        Token token = getCurrentToken(request);
        //用户权限校验
        permissionValid(token,handler);
        return true;
    }

    private void permissionValid(Token token, Object handler) {
        HttpServletRequest globalHttpRequest = GlobalHttpUtils.getGlobalHttpRequest();
        HandlerMethod method = (HandlerMethod) handler;
        AccessPermission methodAnnotation = method.getMethodAnnotation(AccessPermission.class);
        String[] roles = null;
        if (methodAnnotation != null) {
             roles = methodAnnotation.roleName();
        }
        //获取当前用户所需的角色
        UserInfo userInfo = userMapper.getUserInfo(token.getId());

        //不需要权限
        if (roles == null) {
            token.setRoles(userInfo.getRolePOS());
            globalHttpRequest.setAttribute("token",token);
            return;
        }
        HashSet<String> roleName = new HashSet<>();
        //超级管理员才能访问的
        for (Role role : userInfo.getRolePOS()) {
            roleName.add(role.getRoleName());
        }
        if (roleName.contains(ADMIN)) {
            token.setRoles(userInfo.getRolePOS());
            globalHttpRequest.setAttribute("token",token);
            return;
        }

        for (String role : roles) {
            if (!roleName.contains(role)) {
                throw new ExceptionFactory(ResponseCode.NO_ACCESS_PERMISSION);
            }
        }
        token.setRoles(userInfo.getRolePOS());
        globalHttpRequest.setAttribute("token",token);
    }

    /**
     * Toke黑名单验证
     *
     * @param request
     */
    private void verifyBlackTokenList(HttpServletRequest request) {
        Map<String, Cookie> stringCookieMap = ReadCookieMap(request);
        List<Object> objects = redisUtils.opsForListGetValue(BLACK_TOKEN__LIST);
        for (int i = 0; i < objects.size(); i++) {
            Object token = objects.get(i);
            if (stringCookieMap.containsKey(TOKEN) && stringCookieMap.get(TOKEN).getValue().equals(token)) {
                log.error("账号已退出，此token为黑名单token");
                throw new ExceptionFactory(ResponseCode.TOKEN_LOGOUT);
            }
        }
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



    private Token getCurrentToken(HttpServletRequest request) {
        Map<String, Cookie> stringCookieMap = ReadCookieMap(request);
        if (!stringCookieMap.containsKey("token")) {
            throw new ExceptionFactory(ResponseCode.NO_TOKEN);
        }
        //验证token
        Token token = tokenService.verifyToken(stringCookieMap.get("token").getValue());
        //request中的attribute中设置token
        request.setAttribute("token",token);
        return token;
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
