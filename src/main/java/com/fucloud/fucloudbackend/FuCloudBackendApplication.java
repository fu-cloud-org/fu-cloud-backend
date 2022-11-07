package com.fucloud.fucloudbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fucloud.fucloudbackend.dao")
public class FuCloudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuCloudBackendApplication.class, args);
    }

}
