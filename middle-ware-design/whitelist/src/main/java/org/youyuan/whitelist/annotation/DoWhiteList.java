package org.youyuan.whitelist.annotation;

import java.lang.annotation.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/4/17 14:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DoWhiteList {
    String key() default "";
    String returnJson() default "";
}
