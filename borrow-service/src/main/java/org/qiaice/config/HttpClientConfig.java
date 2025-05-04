package org.qiaice.config;

import org.apache.seata.core.context.RootContext;
import org.qiaice.service.client.BookClient;
import org.qiaice.service.client.UserClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .requestInterceptor((request, bytes, execution) -> {
                    request.getHeaders().add(RootContext.KEY_XID, RootContext.getXID());
                    return execution.execute(request, bytes);
                });
    }

    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(RestClient.Builder builder) {
        RestClientAdapter adapter = RestClientAdapter.create(builder.build());
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }

    @Bean
    public UserClient userClient(HttpServiceProxyFactory factory) {
        return factory.createClient(UserClient.class);
    }

    @Bean
    public BookClient bookClient(HttpServiceProxyFactory factory) {
        return factory.createClient(BookClient.class);
    }
}
