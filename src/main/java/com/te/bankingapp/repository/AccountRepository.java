package com.te.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
