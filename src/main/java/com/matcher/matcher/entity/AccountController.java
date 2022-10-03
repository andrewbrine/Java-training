package com.matcher.matcher.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    private List<Account>  getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    private Account getAccount(@PathVariable("id") int id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/accounts/{id}")
    private List<Account> deleteAccount(@PathVariable("id") int id){
        accountService.delete(id);
        return accountService.findAll();
    }

    @PostMapping("/accounts")
    private ResponseEntity<Account> saveAccount(@RequestBody @Valid Account account) {
        System.out.println(account.getId());

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
