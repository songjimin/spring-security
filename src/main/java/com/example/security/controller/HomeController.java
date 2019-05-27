package com.example.security.controller;

import com.example.security.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

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

    @PostMapping("joinProcessing")
    public String joinProcessing(@Param("account") String userName, @Param("password") String password) {

        if (accountService.joinAccount(userName, password)) {
            return "redirect:/";
        } else {
            return "redirect:/join";
        }
    }
}
