package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Transactional
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public Account find(long id) {
      return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional
    @Override
    public Account delete(long id) {
      Account account =find(id);
      accountRepository.delete(account);
      return account;
    }
}

