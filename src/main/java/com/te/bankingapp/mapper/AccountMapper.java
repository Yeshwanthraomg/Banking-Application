package com.te.bankingapp.mapper;

import com.te.bankingapp.dto.AccountDto;
import com.te.bankingapp.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		if (accountDto == null) {
			return null;
		}

		return new Account(accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getBalance());
	}

	public static AccountDto mapToAccountDto(Account account) {
		if (account == null) {
			return null;
		}
		return new AccountDto(account.getId(), account.getAccountHolderName(), account.getBalance());
	}
}
