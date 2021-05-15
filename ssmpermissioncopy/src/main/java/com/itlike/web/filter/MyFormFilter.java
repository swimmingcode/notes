package com.itlike.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itlike.domain.Ajax;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*判断是登录成功还是失败*/
public class MyFormFilter extends FormAuthenticationFilter {

    /*成功*/
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        /*响应给浏览器*/
        Ajax ajaxRes = new Ajax(true, "登录成功");
        /*System.out.println(ajaxRes);*/
        /*把对象转化成json格式的字符串*/
        String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
        response.getWriter().print(jsonString);
        return false;

    }
    /*失败*/
    @SneakyThrows
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        response.setCharacterEncoding("utf-8");
        Ajax ajaxRes = new Ajax();
        ajaxRes.setSuccess(false);
        if (e!=null){
            /*获取异常名称*/
            String name = e.getClass().getName();
            if (name.equals(UnknownAccountException.class.getName())){
                ajaxRes.setMessage("账号不正确");
            }else if(name.equals(IncorrectCredentialsException.class.getName())){
                ajaxRes.setMessage("密码不正确");
            }else{
                ajaxRes.setMessage("未知错误");
            }
        }
        /*把对象转化成json格式的字符串*/
        String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
        response.getWriter().print(jsonString);
        return false;
    }
}
