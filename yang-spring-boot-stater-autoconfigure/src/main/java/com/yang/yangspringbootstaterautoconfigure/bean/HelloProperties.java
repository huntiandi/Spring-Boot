package com.yang.yangspringbootstaterautoconfigure.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ProjectName: com.yang.yangspringbootstaterautoconfigure.bean
 * @author: ZhangBiBo
 * @description: 与配置类关联的bean
 * @data: 2022/3/14
 */
@ConfigurationProperties(prefix = "yang.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
