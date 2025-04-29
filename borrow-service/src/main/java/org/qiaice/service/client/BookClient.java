package org.qiaice.service.client;

import org.qiaice.entity.Book;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(value = "http://book-service/api/book")
public interface BookClient {

    @GetExchange(value = "/{bid}")
    Book findByBid(@PathVariable Integer bid);

    @GetExchange(value = "/count/{bid}")
    Integer getCountByBid(@PathVariable Integer bid);

    @GetExchange(value = "/borrow/{bid}")
    Boolean borrow(@PathVariable Integer bid);
}
