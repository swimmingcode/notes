package org.youyuan.web.assemble;

import org.springframework.context.annotation.Import;
import org.youyuan.web.assemble.model.Red;

import java.lang.annotation.*;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/4 17:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Red.class,ColorRegistrarConfiguration.class,ColorImportSelector.class,ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
