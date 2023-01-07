package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2022/12/14
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    //postman自测，参数不需要加上@RequestBody，http://localhost:8001/payment/save?serial=atguigu06
    //RestTemplate访问，参数需要加上@RequestBody
    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment) {

        int result = paymentService.save(payment);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功，serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    //http://localhost:8001/payment/get/2
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> selectById(@PathVariable("id") Long id) {

        Payment payment = paymentService.selectById(id);
        log.info("******查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******service: " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }

    /**
     * 【Ribbon之手写轮询算法】测试
     *
     * @return
     */
    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /**
     * OpenFeign超时控制之测试
     *
     * @return
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //线程暂停几秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
