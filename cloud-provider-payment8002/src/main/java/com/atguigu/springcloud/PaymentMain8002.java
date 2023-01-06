package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description: TODO 类描述
 * 1.加上 @EnableEurekaClient ，【支付模块8001】要入驻【注册中心EurekaServer7001】
 * 2.启动服务【注册中心模块EurekaServer7001】、【支付模块8001】
 * 3.http://localhost:7001/ 可以看到 【支付模块8001】服务
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2022/12/14
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class PaymentMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8002.class, args);
    }
}
