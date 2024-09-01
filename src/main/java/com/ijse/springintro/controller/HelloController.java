package com.ijse.springintro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello world";
    }

    @PostMapping("/hello")
    public String createHello() {
        return "POST Request to hello endpoint";
    }
}
