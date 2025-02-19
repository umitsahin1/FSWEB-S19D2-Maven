package com.workintech.s18d4.controller;


import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable("customerId") Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);

        if (customer == null) {
            throw new RuntimeException("No customer found with id: " + customerId);
        }

        customer.getAccounts().add(account);
        account.setCustomer(customer);
        Account savedAccount = accountService.save(account);

        return new AccountResponse(savedAccount.getId(), savedAccount.getAccountName(), savedAccount.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()
                )
        );
    }


    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable Long customerId ){
        Customer customer = customerService.find(customerId);
        Account toBeUpdateAccount= null;

        for(Account account1: customer.getAccounts()){
            if(account.getId() == (account1.getId())){
                toBeUpdateAccount =account1;
            }
        }

        if(toBeUpdateAccount == null){
            throw new RuntimeException("Account("+account.getId()+") for this customer: " + customerId);
        }

        int indexOfToBeUpdate=customer.getAccounts().indexOf(toBeUpdateAccount);
        customer.getAccounts().set(indexOfToBeUpdate,account);
        account.setCustomer(customer);
        accountService.save(account);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary()));
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id){
        Account account = accountService.find(id);
      if(account == null){
          throw  new RuntimeException("No account found");
      }
      accountService.delete(id);
      return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
              new CustomerResponse(account.getCustomer().getId(),account.getCustomer().getEmail(),account.getCustomer().getSalary()));
    }
}
