package com.matcher.matcher.entity.account;

import com.matcher.matcher.entity.account.Account;
import com.matcher.matcher.entity.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account getAccountById(long id) {
        return repository.findById(id).get();
    }

    public Account saveOrUpdate(Account account){
        repository.save(account);
        return account;
    }

    public void delete(long id){
        repository.deleteById(id);
    }
}
