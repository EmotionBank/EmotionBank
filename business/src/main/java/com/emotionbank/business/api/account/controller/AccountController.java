package com.emotionbank.business.api.account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.account.dto.CheckAccountBalanceDto;
import com.emotionbank.business.api.account.dto.UpdateAccountDto;
import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	@GetMapping("/balance")
	public ResponseEntity<CheckAccountBalanceDto.Response> getAccountBalance(@RequestBody CheckAccountBalanceDto.Request request) {
		AccountDto accountDto = accountService.getAccountBalance(request.getAccountNumber());
		return ResponseEntity.ok(CheckAccountBalanceDto.Response.from(accountDto));
	}

	@PutMapping
	public ResponseEntity<?> updateAccountName(@RequestBody UpdateAccountDto.Request request) {
		accountService.updateAccountName(request.getAccountNumber(), request.getAccountName());
		return ResponseEntity.ok().build();
	}
}
