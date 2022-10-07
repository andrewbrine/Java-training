package com.matcher.matcher.entity.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/accounts")
    private List<Account>  getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    private Account getAccount(@PathVariable("id") long id) {

        return accountService.getAccountById(id);
    }

    @DeleteMapping("/accounts/{id}")
    private List<Account> deleteAccount(@PathVariable("id") long id){
        accountService.delete(id);
        return accountService.findAll();
    }

    @PostMapping("/accounts")
    private ResponseEntity<Account> saveAccount(@RequestBody @Valid Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Account savedAccount = accountService.saveOrUpdate(account);
        URI accountURI = URI.create("/accounts/" + savedAccount.getId());
        return ResponseEntity.created(accountURI).body(savedAccount);
    }

    @GetMapping("/auto-add-account")
    private void autoAdd() {
        Account account = new Account("username", "password");
        accountService.saveOrUpdate(account);
    }

}
