package org.qiaice.config;

import org.apache.seata.core.context.RootContext;
import org.qiaice.service.client.BookClient;
import org.qiaice.service.client.UserClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, bytes, execution) -> {
            request.getHeaders().add("TX_XID", RootContext.getXID());
            return execution.execute(request, bytes);
        };
    }

    @Bean
    public UserClient userClient(
            RestClient.Builder builder,
            @Qualifier("clientHttpRequestInterceptor") ClientHttpRequestInterceptor interceptor
    ) {
        RestClient client = builder.requestInterceptor(interceptor).baseUrl("http://user-service").build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(UserClient.class);
    }

    @Bean
    public BookClient bookClient(
            RestClient.Builder builder,
            @Qualifier("clientHttpRequestInterceptor") ClientHttpRequestInterceptor interceptor
    ) {
        RestClient client = builder.requestInterceptor(interceptor).baseUrl("http://book-service").build();
        RestClientAdapter adapter = RestClientAdapter.create(client);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BookClient.class);
    }
}
