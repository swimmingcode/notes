package org.youyuan.websocket.websocket.singlechat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author YJP
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加controller视图
     * /ws -> ws.html
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/ws").setViewName("/ws");
    }


}
