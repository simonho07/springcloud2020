package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2022/12/14
 **/
public interface PaymentService {

    public int save(Payment payment);

    public Payment selectById(Long id);
}
