package org.qiaice;

import org.qiaice.config.LoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@LoadBalancerClient(value = "user-service", configuration = LoadBalancerConfig.class)
public class BorrowApplication {

    // 注解 @LoadBalancerClient 配置 user-service 服务使用指定的随机负载均衡器
    // 有时局部负载均衡器(@LoadBalancerClient 打在配置类上)不会生效(版本问题)
    // 设置成全局负载均衡器即可(@LoadBalancerClient 打在启动类上)

    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class, args);
    }
}
