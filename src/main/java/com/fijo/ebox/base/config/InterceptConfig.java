package com.fijo.ebox.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "intercept")
public class InterceptConfig {
    private Boolean isOpenintercept;//是否开始请求拦截
}
