package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    //postman自测，参数不需要加上@RequestBody，http://localhost:8001/payment/save?serial=atguigu06
    //RestTemplate访问，参数需要加上@RequestBody
    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment) {

        int result = paymentService.save(payment);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
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
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
        }
    }

}
