package com.ecommercebackend.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/web")
public class Web {
    @GetMapping(value = "")
    public void index() {

    }
}
