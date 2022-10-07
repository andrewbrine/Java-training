package com.matcher.matcher.entity.account;

import com.matcher.matcher.entity.account.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();

    Optional<Account> findByUsername(String username);

}

