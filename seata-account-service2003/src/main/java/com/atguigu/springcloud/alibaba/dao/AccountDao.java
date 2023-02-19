package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/2/19
 **/
@Mapper
public interface AccountDao {

    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
