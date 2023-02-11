package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.myHandler.CustomizeBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/11
 **/
@RestController
public class RateLimitController {

    /**
     * 配置兜底方法：
     * 资源名称限流。sentinel控制台配置流控规则时，资源名：byResource。走兜底方法handleException
     * url限流。控制台配置流控规则时，资源名：/rateLimit/byResource，抛出：Blocked by Sentinel (flow limiting)
     *
     * @return
     */
    @GetMapping("/rateLimit/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {

        return new CommonResult(200, "按资源名称限流------测试OK", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException exception) {

        return new CommonResult<>(444, exception.getClass().getCanonicalName() + "\t服务不可用");
    }

    /**
     * 不配置兜底方法：
     * url限流。sentinel控制台配置流控规则时，资源名：/rateLimit/byUrl，抛出：Blocked by Sentinel (flow limiting)
     * 资源名称限流。控制台配置流控规则时，资源名：byUrl，抛出 UndeclaredThrowableException
     *
     * @return
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {

        return new CommonResult(200, "按url限流------测试OK", new Payment(2020L, "serial002"));
    }

    /**
     * 自定义限流处理
     *
     * @return
     */
    @GetMapping("/rateLimit/testCustomizeBlockHandler")
    @SentinelResource(value = "testCustomizeBlockHandler",
            blockHandlerClass = CustomizeBlockHandler.class,
            blockHandler = "handleException2")
    public CommonResult testCustomizeBlockHandler() {

        return new CommonResult(200, "按客户自定义", new Payment(2020L, "serial003"));
    }
}
