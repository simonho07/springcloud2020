package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2022/12/14
 **/
@Data
//@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {

    private int code;
    private String message;
    private T data;

    public CommonResult(int code, String message) {
        this(code, message, null);
    }
}

