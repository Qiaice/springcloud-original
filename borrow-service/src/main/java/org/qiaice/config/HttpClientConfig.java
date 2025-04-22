package org.qiaice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {

    @Bean
    @LoadBalanced // 自动启用负载均衡设置(目前仅用于从 eureka server 中获取服务地址)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
