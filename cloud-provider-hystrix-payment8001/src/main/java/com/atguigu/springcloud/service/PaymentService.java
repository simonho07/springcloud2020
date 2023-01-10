package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/1/8
 **/
@Service
public class PaymentService {

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(int id) {

        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_OK,id:  " + id + "\t" + "O(∩_∩)O哈哈~";
    }

    /**
     * 1.模拟复杂的业务处理，超时异常
     * 2.模拟程序处理出错，计算异常
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentInfo_Timeout(int id) {

        int timeNumber = 3000;
        try {
            TimeUnit.MILLISECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id
                + "\t" + "O(∩_∩)O哈哈~" + "耗时(ms)：" + timeNumber;
/*
        int age = 10 / 0;
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id;
        */
    }

    //当前服务不可用了，做服务降级，兜底的方案是paymentInfo_TimeoutHandler
    public String paymentInfo_TimeoutHandler(int id) {

        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_TimeoutHandler " + "8001系统繁忙，请稍后再试！";

    }

    //======服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitbreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到阈值跳闸
    })
    public String paymentCircuitbreaker(@PathVariable("id") int id) {

        if (id < 0) {
            throw new RuntimeException("id不能为负数！！！");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitbreaker_fallback(@PathVariable("id") int id) {

        return "id不能为负数，请稍后再试。id：" + id;
    }
}
