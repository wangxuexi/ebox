package com.fijo.ebox.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "fileaddr")
public class FileConfig {

    private String ftpPath;//ftp文件存放物理路径

    private String ftpHost;//邮箱地址

    private String ftpPort; // ftp服务员器端口号

    private String ftpUserName; // 登录用户

    private String ftpPassword; //登录密码


}
