package org.qiaice.controller;

import lombok.AllArgsConstructor;
import org.qiaice.entity.Book;
import org.qiaice.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/book", produces = "application/json; charset=utf-8")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "/{bid}")
    public Book findByBid(@PathVariable Integer bid) {
        return bookService.getById(bid);
    }

    @GetMapping(value = "/count/{bid}")
    public Integer getCountByBid(@PathVariable Integer bid) {
        return bookService.getById(bid).getCount();
    }

    @Transactional
    @GetMapping(value = "/borrow/{bid}")
    public Boolean borrow(@PathVariable Integer bid) {
        Integer count = bookService.getById(bid).getCount();
        return bookService.lambdaUpdate().set(Book::getCount, count - 1).eq(Book::getBid, bid).update();
    }
}
