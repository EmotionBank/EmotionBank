package com.emotionbank.business.api.transaction.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.transaction.dto.GetTransactionDetailDto;
import com.emotionbank.business.api.transaction.dto.GetTransactionListDto;
import com.emotionbank.business.api.transaction.dto.TransferDto;
import com.emotionbank.business.api.transaction.dto.UpdateBalanceDto;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.dto.TransactionTransferDto;
import com.emotionbank.business.domain.transaction.service.TransactionService;
import com.emotionbank.business.global.jwt.annotation.UserInfo;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<UpdateBalanceDto.Response> updateBalance(
		@RequestBody UpdateBalanceDto.Request request, @UserInfo UserInfoDto userInfoDto) {
		TransactionDto transactionDto = transactionService.updateBalance(TransactionDto.from(request),
			userInfoDto.getUserId());
		return ResponseEntity.ok(UpdateBalanceDto.Response.from(transactionDto));
	}

	@GetMapping
	public ResponseEntity<GetTransactionListDto.Response> getTransactions(GetTransactionListDto.Request request,
		@UserInfo UserInfoDto userInfoDto) {
		List<TransactionDto> transactionList = transactionService.getTransactions(
			TransactionSearchDto.of(request, userInfoDto.getUserId()));
		return ResponseEntity.ok(GetTransactionListDto.Response.from(transactionList));
	}

	@GetMapping("/{transactionId}")
	public ResponseEntity<GetTransactionDetailDto.Response> getTransactionDetail(@PathVariable Long transactionId,
		@UserInfo UserInfoDto userInfoDto) {
		TransactionDto transactionDto = transactionService.getTransactionDetail(transactionId, userInfoDto.getUserId());
		TransactionType transactionType = transactionDto.getTransactionType();

		if (TransactionType.DEPOSIT.equals(transactionType)) {
			return ResponseEntity.ok(GetTransactionDetailDto.Response.of(transactionDto, transactionDto.getSender()));
		} else if (TransactionType.WITHDRAWL.equals(transactionType)) {
			return ResponseEntity.ok(GetTransactionDetailDto.Response.of(transactionDto, transactionDto.getReceiver()));
		}

		return ResponseEntity.badRequest().build();
	}

	@PostMapping("/transfer")
	public ResponseEntity<TransferDto.Response> transfer(@UserInfo UserInfoDto userInfoDto,
		@RequestBody TransferDto.Request request) {
		long balance = transactionService.transfer(
			TransactionTransferDto.of(userInfoDto.getUserId(), request.getReceiver(),
				request.getAmount(), request.getEmoticon()));
		return ResponseEntity.ok(TransferDto.Response.of(balance));
	}
}