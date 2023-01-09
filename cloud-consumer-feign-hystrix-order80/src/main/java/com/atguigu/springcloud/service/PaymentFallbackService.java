package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/1/9
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(int id) {
        return "PaymentFallbackService.paymentInfo_OK() fall back";
    }

    @Override
    public String paymentInfo_Timeout(int id) {
        return "PaymentFallbackService.paymentInfo_Timeout() fall back";
    }
}
