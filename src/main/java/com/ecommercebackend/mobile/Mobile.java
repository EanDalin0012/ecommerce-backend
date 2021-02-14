package com.ecommercebackend.mobile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/mobile")
public class Mobile {
    @GetMapping(value = "/")
    public void index() {

    }
}
