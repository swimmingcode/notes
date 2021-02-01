package org.youyuan.jwt.utils.jwt.annotation;

import java.lang.annotation.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @Date: 2021/2/1 12:12
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface UnLogin {
}
