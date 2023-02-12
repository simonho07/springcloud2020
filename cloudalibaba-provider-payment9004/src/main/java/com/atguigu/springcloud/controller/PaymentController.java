package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/12
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //模拟数据库
    private static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();

    static {
        hashMap.put(1L, new Payment(1L, "serial-hhzh-001"));
        hashMap.put(2L, new Payment(2L, "serial-hhzh-002"));
        hashMap.put(3L, new Payment(3L, "serial-hhzh-003"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {

        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200, " from mysql, server port:    " + serverPort, payment);
        return result;
    }
}
