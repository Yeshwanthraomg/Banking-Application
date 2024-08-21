package com.te.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.bankingapp.dto.AccountDto;
import com.te.bankingapp.dto.ResponseDto;
import com.te.bankingapp.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Add Account Rest API
    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.createAccount(accountDto);
        ResponseDto responseDto = new ResponseDto(createdAccount, "Account created successfully", HttpStatus.CREATED.value());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Get Account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        ResponseDto responseDto = new ResponseDto(accountDto, "Account retrieved successfully", HttpStatus.OK.value());
        return ResponseEntity.ok(responseDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<ResponseDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        ResponseDto responseDto = new ResponseDto(accountDto, "Deposit successful", HttpStatus.OK.value());
        return ResponseEntity.ok(responseDto);
    }

    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<ResponseDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        ResponseDto responseDto = new ResponseDto(accountDto, "Withdrawal successful", HttpStatus.OK.value());
        return ResponseEntity.ok(responseDto);
    }

    // GetAll REST API
    @GetMapping("/getall")
    public ResponseEntity<ResponseDto> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccounts();
        ResponseDto responseDto = new ResponseDto(accountDtos, "Accounts retrieved successfully", HttpStatus.OK.value());
        return ResponseEntity.ok(responseDto);
    }

    // Delete REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        ResponseDto responseDto = new ResponseDto(null, "Account deleted successfully", HttpStatus.NO_CONTENT.value());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseDto);
    }
}
