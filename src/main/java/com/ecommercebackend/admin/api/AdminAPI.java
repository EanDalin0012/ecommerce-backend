package com.ecommercebackend.admin.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/v1")
public class AdminAPI {
    @GetMapping(value = "/")
    public void index() {

    }
}
