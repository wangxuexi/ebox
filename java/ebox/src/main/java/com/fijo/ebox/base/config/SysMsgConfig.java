package com.fijo.ebox.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sysmsg")
public class SysMsgConfig {
    private String fromMail;//发送邮箱地址
}
