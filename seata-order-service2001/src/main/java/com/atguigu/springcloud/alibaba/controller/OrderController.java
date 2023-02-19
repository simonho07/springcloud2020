package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {

        orderService.create(order);
        return new CommonResult(200, "创建订单成功！");
    }
}
