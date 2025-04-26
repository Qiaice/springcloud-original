package org.qiaice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.AllArgsConstructor;
import org.qiaice.entity.User;
import org.qiaice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/user", produces = "application/json; charset=utf-8")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{uid}")
    public User findByUid(@PathVariable Integer uid) {
        return userService.getById(uid);
    }

    @GetMapping(value = "/block")
    public Map<String, Serializable> blockHandler() {
        return Map.of(
                "code", 400,
                "msg", "您的访问过于频繁, 请稍后再试",
                "data", "null"
        );
    }

    @GetMapping(value = "/test")
    @SentinelResource(value = "test", blockHandler = "testBlockHandler")
    public Map<String, Serializable> test() {
        return Map.of(
                "code", 200,
                "msg", "资源访问成功",
                "data", "null"
        );
    }

    public Map<String, Serializable> testBlockHandler(BlockException e) {
        return blockHandler();
    }
}
