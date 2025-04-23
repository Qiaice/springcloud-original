package org.qiaice.service.client;

import org.qiaice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service")
public interface UserClient {

    @GetMapping(value = "/api/user/{uid}")
    User findByUid(@PathVariable Integer uid);
}
