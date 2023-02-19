package com.atguigu.springcloud.alibaba.service;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
public interface StorageService {

    void decrease(Long productId, Integer count);
}
