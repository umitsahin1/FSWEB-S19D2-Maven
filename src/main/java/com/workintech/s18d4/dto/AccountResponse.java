package com.workintech.s18d4.dto;

import com.workintech.s18d4.service.CustomerService;

public record AccountResponse(long id, String accountName, Double moneyAmount, CustomerResponse customerResponse) {
}
