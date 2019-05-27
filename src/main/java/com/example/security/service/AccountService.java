package com.example.security.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import com.example.security.model.Account;
import com.example.security.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(String userName, String password) {
        Account account = new Account();
        account.setUserName(userName);
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }


    /*
        로그인을 시도한 유저의 정보(username)을 받아 비밀번호를 확인하는 interface (UserDetailsService)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> userInfo = accountRepository.findByUserName(username);
        Account account = userInfo.orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(account.getUserName(), account.getPassword(), authorities());
    }

    //권한들을 의미
    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }


    public boolean joinAccount(String userName, String password) {

        Optional<Account> candidateAccount = accountRepository.findByUserName(userName);
        if (candidateAccount.isPresent()) {
            return false;
        } else {
            createAccount(userName, password);
            return true;
        }
    }
}
