package org.qiaice.service.client;

import org.qiaice.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "book-service")
public interface BookClient {

    @GetMapping(value = "/api/book/{bid}")
    Book findByBid(@PathVariable Integer bid);
}
