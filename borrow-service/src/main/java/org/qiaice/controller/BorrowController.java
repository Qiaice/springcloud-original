package org.qiaice.controller;

import lombok.AllArgsConstructor;
import org.qiaice.service.BorrowService;
import org.qiaice.vo.UserBorrowVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/borrow", produces = "application/json; charset=utf-8")
@AllArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping(value = "/{uid}")
    public UserBorrowVO findByUid(@PathVariable Integer uid) {
        return borrowService.findByUid(uid);
    }
}
