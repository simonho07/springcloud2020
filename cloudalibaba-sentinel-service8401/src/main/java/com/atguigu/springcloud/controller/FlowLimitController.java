package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/10
 **/
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {

        return "---------------------testA";
    }

    @GetMapping("/testB")
    public String testB() {

        log.info(Thread.currentThread().getName() + "\t" + "......testB");
        return "---------------------testB";
    }

    @GetMapping("/testD")
    public String testD() {

        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("------testD测试RT");*/

        log.info("------testD测试 异常比例");
        int i = 10 / 0;
        return "---------------------testD";
    }

    @GetMapping("/testE")
    public String testE() {

        log.info("------testD测试 异常数");
        int i = 10 / 0;
        return "---------------------testE";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_HotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

        return "---------------------testHotKey";
    }

    public String deal_HotKey(String p1, String p2, BlockException exception) {

        return "---------------------热点数据兜底方法：deal_HotKey";
    }
}
