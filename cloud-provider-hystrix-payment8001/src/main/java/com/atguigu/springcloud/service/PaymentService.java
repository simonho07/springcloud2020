package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
     * 1.模拟复杂的业务处理，需要时间5s，超时异常
     * 2.模拟程序处理出错，计算异常
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String paymentInfo_Timeout(int id) {

        /*int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id
                + "\t" + "O(∩_∩)O哈哈~" + "耗时(s)：" + timeNumber;*/

        int age = 10 / 0;
        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_Timeout,id:  " + id;
    }

    //当前服务不可用了，做服务降级，兜底的方案是paymentInfo_TimeoutHandler
    public String paymentInfo_TimeoutHandler(int id) {

        return "线程池:  " + Thread.currentThread().getName() + "  paymentInfo_TimeoutHandler " + "服务繁忙，请稍后再试！";

    }
}
