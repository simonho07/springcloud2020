package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description: TODO Eureka Server服务集群
 * 1.启动服务模块【eureka7001】、【eureka7002】
 * 2.http://eureka7001.com:7001/ 可以看到【eureka7002】
 * 3.http://eureka7002.com:7002/ 可以看到【eureka7001】
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2022/12/15
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7002.class, args);
    }
}
