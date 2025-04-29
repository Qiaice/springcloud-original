package org.qiaice.service.client;

import org.qiaice.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(value = "/api/user")
public interface UserClient {

    @GetExchange(value = "{uid}")
    User findByUid(@PathVariable Integer uid);

    @GetExchange(value = "hold/{uid}")
    Integer getHoldByUid(@PathVariable Integer uid);

    @GetExchange(value = "/borrow/{uid}")
    Boolean borrow(@PathVariable Integer uid);
}
