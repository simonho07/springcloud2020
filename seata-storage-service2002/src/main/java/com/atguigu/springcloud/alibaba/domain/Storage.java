package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {

    private Long id;

    //产品id
    private Long productId;

    //总库存
    private Integer total;

    //已用库存
    private Integer used;

    //剩余库存
    private Integer residue;
}
