package com.atguigu.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @description: TODO 自定义限流处理类
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/11
 **/
public class CustomizeBlockHandler {

    public static CommonResult handleException1(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handleException----1");
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(4444, "按客戶自定义,global handleException----2");
    }

}
