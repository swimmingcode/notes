package com.southwind.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * sentinel授权配置
 * 为了方便测试：不注入
 */
//@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void init(){
        WebCallbackManager.setRequestOriginParser(new RequestOriginParserDefinition());
    }
}