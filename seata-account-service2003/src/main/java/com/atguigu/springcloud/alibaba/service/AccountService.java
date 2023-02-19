package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
