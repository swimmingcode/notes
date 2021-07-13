package org.youyuan.demo;

/**
 * @Describe: #请描述当前类的功能#
 * @Author: youjiancheng
 * @date 2021/5/19 11:44
 */
public class CodeService {
    CodeProperties codeProperties;

    public CodeService(CodeProperties codeProperties) {
        this.codeProperties = codeProperties;
    }

    public CodeProperties getCodeProperties() {
        return codeProperties;
    }

    public void setCodeProperties(CodeProperties codeProperties) {
        this.codeProperties = codeProperties;
    }

    public String hello() {
        return codeProperties.getCode()+codeProperties.getName();
    }
}
