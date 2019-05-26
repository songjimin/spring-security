package com.example.security;

import com.example.security.model.Account;
import com.example.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {

    @Autowired
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account jimin = accountService.createAccount("jimin", "1234");
        System.out.println("Account id, pw : " + jimin.getUserName() + ", " + jimin.getPassword());
    }
}
