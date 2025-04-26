package org.qiaice.controller;

import lombok.AllArgsConstructor;
import org.qiaice.service.BorrowService;
import org.qiaice.vo.UserBorrowVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/borrow", produces = "application/json; charset=utf-8")
@AllArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping(value = "/{uid}")
    public UserBorrowVO findByUid(@PathVariable Integer uid) {
        return borrowService.findByUid(uid);
    }

    @GetMapping(value = "/{uid}/{bid}")
    public Map<String, Serializable> borrow(@PathVariable Integer uid, @PathVariable Integer bid) {
        borrowService.borrow(uid, bid);
        return Map.of(
                "code", 200,
                "msg", "借阅成功",
                "data", "null"
        );
    }
}
