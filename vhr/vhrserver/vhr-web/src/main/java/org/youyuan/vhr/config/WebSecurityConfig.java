package org.youyuan.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.youyuan.vhr.bean.Hr;
import org.youyuan.vhr.bean.RespBean;
import org.youyuan.vhr.service.HrService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    HrService hrService;

    @Autowired
    CustomFilterInvocationSecurityMetadatabase metadatabase;

    @Autowired
    CustomAccessDecisionManager manager;

    /**
     * 加密
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 如果要访问/login页面 ，不用经过Spring Security拦截
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
        //web.ignoring().antMatchers("/index.html");
        web.ignoring().antMatchers("/js/**", "/css/**", "/font/**", "/img/**", "/index.html", "/favicon.ico");
    }

    /**
     * 认证用户与授权
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启配置
        http.authorizeRequests()
                //所有请求都需要认证
                .anyRequest().authenticated()
                /**
                 * 所有的请求都经过FilterInvocationSecurityMetadataSource与AccessDecisionManager
                 * 1.如果没有登录访问
                 *  - 若访问的地址不存在 返回ROLE_LOGIN ,身份为认证，AccessDecisionManager就会抛出一个异常 throw new AccessDeniedException("尚未登录，请登录") ,然后进入loginPage的url
                 *  - 若访问的地址存在，返回一个 Collection<ConfigAttribute>对象，遍历用户时，无此角色，抛出异常，然后进入loginPage的url
                 * 2.如果登录访问时
                 * - 若访问的地址不存在 ，返回ROLE_LOGIN，身份已认证，直接return，404 不做任何处理
                 * - 若访问的地址存在，返回一个 Collection<ConfigAttribute>对象，遍历对象时，有所需的角色直接return，若没有，抛出异常 403 throw new AccessDeniedException("权限不足，请联系管理员");
                 * **/
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(metadatabase);
                        object.setAccessDecisionManager(manager);
                        return object;
                    }
                })
                .and()
                .formLogin()
                //  .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("user")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
                        response.setStatus(200);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        Hr hr = (Hr) auth.getPrincipal();
                        //不返回密码
                        hr.setPassword(null);
                        //返回信息（msg+用户信息）
                        RespBean respBean = RespBean.ok("登录成功", hr);
                        ObjectMapper objectMapper = new ObjectMapper();
                        String s = objectMapper.writeValueAsString(respBean);

                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        RespBean respBean = RespBean.error("登录失败");
                        if (e instanceof LockedException) {
                            respBean.setMsg("账户被锁定，登录失败！");
                        } else if (e instanceof BadCredentialsException) {
                            respBean.setMsg("账户名或密码输入错误，请重新输入！");
                        } else if (e instanceof DisabledException) {
                            respBean.setMsg("账户被禁用，登录失败！");
                        } else if (e instanceof AccountExpiredException) {
                            respBean.setMsg("账户已过期，登录失败！");
                        } else if (e instanceof CredentialsExpiredException) {
                            respBean.setMsg("密码已过期，登录失败！");
                        } else {
                            respBean.setMsg("登录失败！");
                        }
                        ObjectMapper objectMapper = new ObjectMapper();
                        String s = objectMapper.writeValueAsString(respBean);

                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                //允许所有用户访问
                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @SneakyThrows
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        RespBean respBean = RespBean.ok("注销成功");
                        String s = new ObjectMapper().writeValueAsString(respBean);
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                })
                .and()
                .csrf()
                .disable()
                .exceptionHandling() //没有登录，在这里处理结果，不要重定向，异常处理
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(401);
                        PrintWriter writer = response.getWriter();
                        RespBean respBean = RespBean.error("访问失败");
                        if (e instanceof InsufficientAuthenticationException) {
                            respBean.setMsg("请求失败，请先登录");
                        }
                        ObjectMapper objectMapper = new ObjectMapper();
                        String s = objectMapper.writeValueAsString(respBean);
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                });
    }
}
