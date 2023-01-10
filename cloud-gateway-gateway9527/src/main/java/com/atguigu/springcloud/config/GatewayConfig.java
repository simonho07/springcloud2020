package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: TODO 类描述
 * @author: hhzh
 * @e-mail: simon.ho07@qq.com
 * @date: 2023/1/11
 **/
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {

        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("https://news.sina.com.cn/china/")).build();
        return routes.build();
    }
}
