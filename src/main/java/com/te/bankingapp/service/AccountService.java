package com.te.bankingapp.service;

import java.util.List;

import com.te.bankingapp.dto.AccountDto;


public interface AccountService {

	
	AccountDto createAccount(AccountDto accountDto);

	AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);
    
    AccountDto withdraw(Long id,double amount);
    
    List<AccountDto> getAllAccounts();

	void deleteAccount(Long id);
}
