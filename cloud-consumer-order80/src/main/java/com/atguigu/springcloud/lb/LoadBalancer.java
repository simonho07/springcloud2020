package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/1/7
 **/
public interface LoadBalancer {

    ServiceInstance getServiceInstance(List<ServiceInstance> instances);
}
