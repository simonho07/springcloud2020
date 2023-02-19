package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
@Mapper
public interface OrderDao {

    //1.创建订单
    void create(Order order);

    //2.修改订单状态，从0改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
