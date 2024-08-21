package com.te.bankingapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.te.bankingapp.dto.AccountDto;
import com.te.bankingapp.dto.ResponseDto;
import com.te.bankingapp.entity.Account;
import com.te.bankingapp.mapper.AccountMapper;
import com.te.bankingapp.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = findAccountById(id);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = findAccountById(id);
		account.setBalance(account.getBalance() + amount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = findAccountById(id);
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds");
		}
		account.setBalance(account.getBalance() - amount);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		return accountRepository.findAll().stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		findAccountById(id);
		accountRepository.deleteById(id);
	}

	private Account findAccountById(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
	}
}
