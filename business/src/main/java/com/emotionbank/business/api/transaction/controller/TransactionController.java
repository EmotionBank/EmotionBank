package com.emotionbank.business.api.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.transaction.dto.UpdateBalanceDto;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<UpdateBalanceDto.Response> deposit(
		@RequestBody UpdateBalanceDto.Request request) {
		TransactionDto transactionDto = transactionService.updateBalance(TransactionDto.from(request));
		return ResponseEntity.ok(UpdateBalanceDto.Response.from(transactionDto));
	}

}