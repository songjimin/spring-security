package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "hello.html";
    }

    @GetMapping("/my")
    public String my() {
        return "my.html";
    }

    @GetMapping("/join")
    public String join() {
        return "join.html";
    }
}
