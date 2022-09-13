package com.fijo.ebox;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 43200,redisNamespace="fijo:plat")
@MapperScan(basePackages = "com.fijo.ebox.modular.*.mapper")
public class EboxApplication {
    public static void main(String[] args) {
        SpringApplication.run(EboxApplication.class, args);
    }
}
