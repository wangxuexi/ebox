package com.fijo.ebox.base.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "approval")
public class ApprovalConfig {
    private Integer expirationTime;//超时时间
}
