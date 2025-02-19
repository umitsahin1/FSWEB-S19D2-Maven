package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;


public interface AccountService {

    Account save(Account account);

    Account find(long id);

    List<Account> findAll();

    Account delete(long id);


}
