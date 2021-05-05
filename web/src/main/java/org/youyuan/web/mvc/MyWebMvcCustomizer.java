package org.youyuan.web.mvc;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Describe: 可替代 application.properties 文件，进行相应的配置
 * @Author: youjiancheng
 * @date 2021/5/5 12:05
 */
@Component
@Order(0)
public class MyWebMvcCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>, Ordered {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.setPort(8088);
//        factory.setContextPath("/demo");
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
