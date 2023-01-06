package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO Ribbon负载规则替换 -- 随机替换默认的轮询
 * 1.这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
 * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了
 * 2.主启动类加上 @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/1/6
 **/
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }
}
