package org.youyuan.jwt.utils.jwt.annotation;

import java.lang.annotation.*;

/**
 * @Describe: 自定义注解Token
 * @Author: youjiancheng
 * @Date: 2021/2/2 19:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER})
@Documented
@Inherited
public @interface RequestToken {
}
