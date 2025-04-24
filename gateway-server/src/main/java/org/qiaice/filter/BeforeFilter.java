package org.qiaice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(0) // 配置全局过滤器的 order, 当与局部过滤器 order 一致时, 全局过滤器优先
@Component
public class BeforeFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String value = exchange.getRequest().getHeaders().getFirst("X-Request-Color");
        System.out.println("Before Filter: " + value);
        return chain.filter(exchange);
    }
}
