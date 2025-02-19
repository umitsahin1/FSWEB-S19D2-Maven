package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    Customer find(long id);

    List<Customer> findAll();

    Customer delete(long id);
}
