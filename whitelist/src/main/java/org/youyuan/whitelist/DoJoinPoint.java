package org.youyuan.whitelist;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.youyuan.whitelist.annotation.DoWhiteList;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/17 14:42
 */
@Aspect
@Slf4j
@Component
public class DoJoinPoint {

    @Resource(name = "whiteListConfig")
    private String whiteListConfig;

    //定义节点
    // 拦截了使用该注解的所有方法
    @Pointcut("@annotation(org.youyuan.whitelist.annotation.DoWhiteList)")
    public void aopPoint() {
    }


    //环绕通知
    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {
        // 获取拦截的方法
        Method method = getMethod(jp);
        //获取注解
        DoWhiteList whiteList = method.getAnnotation(DoWhiteList.class);

        // 获取字段值
        //String keyValue = getFiledValue(whiteList.key(), jp.getArgs());
        String keyValue = ((String) getParamNameAndValue(jp).get(whiteList.key()));

        log.info("middleware whitelist handler method：{} value：{}", method.getName(), keyValue);
        // TODO: 2021/4/18 为什么传入的值为空返回可以访问
        if (null == keyValue || "".equals(keyValue)) {
            return jp.proceed();
        }

        String[] split = whiteListConfig.split(",");

        // 白名单过滤
        for (String str : split) {
            if (keyValue.equals(str)) {
                return jp.proceed();
            }
        }

        // 拦截
        return returnObject(whiteList, method);
    }

    //获取拦截的方法
    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    // 返回对象
    private Object returnObject(DoWhiteList whiteList, Method method) throws IllegalAccessException, InstantiationException {
        //返回的是一个UserInfo
        Class<?> returnType = method.getReturnType();
        String returnJson = whiteList.returnJson();
        if ("".equals(returnJson)) {
            return returnType.newInstance();
        }
        Object o = JSONObject.parseObject(returnJson, returnType);
        return o;
    }

    // 获取属性值
    //为什么要这样获取
    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }


    /**
     * 获取参数名称以及对应的值
     *
     * @param jp
     * @return
     */
    public Map<String,Object> getParamNameAndValue(ProceedingJoinPoint jp) {
        HashMap<String, Object> param = new HashMap<>();
        Object[] paramValue = jp.getArgs();
        String[] parameterNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            param.put(parameterNames[i],paramValue[i]);
        }
        return param;
    }

}
