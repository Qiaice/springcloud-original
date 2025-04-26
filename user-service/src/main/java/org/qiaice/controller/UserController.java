package org.qiaice.controller;

import lombok.AllArgsConstructor;
import org.qiaice.entity.User;
import org.qiaice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json; charset=utf-8")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{uid}")
    public User findByUid(@PathVariable Integer uid) {
        return userService.getById(uid);
    }

    @GetMapping(value = "/hold/{uid}")
    public Integer getHoldByUid(@PathVariable Integer uid) {
        return userService.getById(uid).getHold();
    }

    @GetMapping(value = "/borrow/{uid}")
    public Boolean borrow(@PathVariable Integer uid) {
        Integer hold = userService.getById(uid).getHold();
        return userService.lambdaUpdate().set(User::getHold, hold + 1).eq(User::getUid, uid).update();
    }
}
