package org.youyuan.hystrix.anno;

import java.lang.annotation.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/6 10:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DoHystrix {
    // 失败结果 JSON
    String returnJson() default "";

    // 超时熔断
    int timeoutValue() default 0;
}
