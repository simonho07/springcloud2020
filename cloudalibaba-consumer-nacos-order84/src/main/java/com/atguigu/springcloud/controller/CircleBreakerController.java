package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/12
 **/
@RestController
@Slf4j
public class CircleBreakerController {

    @Value("${service-url.nacos-user-service}")
    private String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有任何配置
    //@SentinelResource(value = "fallback", fallback = "handleFallback")//只配置fallback，fallback负责业务异常
    //@SentinelResource(value = "fallback", blockHandler = "handleBlockHandler")//只配置blockHandler，blockHandler负责sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handleFallback", blockHandler = "handleBlockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})//忽略指定异常，即这些异常不用兜底方法处理
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {

        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 空指针异常, 该id没有对应记录....");
        }
        return result;
    }

    //本例是fallback
    public CommonResult handleFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常handleFallback,exception内容  " + e.getMessage(), payment);
    }

    //本例是blockHandler
    public CommonResult handleBlockHandler(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流,blockException: " + blockException.getMessage(), payment);
    }
}
