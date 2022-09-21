package com.fijo.ebox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "temdetailsurl")
public class TemDatailsUrlConfig {

    private String detailsUrl;  //模板跳转详情页
}
