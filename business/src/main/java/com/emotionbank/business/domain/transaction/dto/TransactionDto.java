package com.emotionbank.business.domain.transaction.dto;

import java.time.LocalDateTime;

import com.emotionbank.business.api.transaction.dto.UpdateBalanceDto;
import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.entity.Transaction;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionDto {
	private Long transactionId;
	private TransactionType transactionType;
	private Long categoryId;
	private String categoryName;
	private Long amount;
	private Long balance;
	private String title;
	private String content;
	private LocalDateTime transactionTime;
	private Emoticon emoticon;
	private String sender;
	private String receiver;
	private Visibility visibility;

	@Builder
	public TransactionDto(Long transactionId, TransactionType transactionType, Long categoryId, String categoryName,
		Long amount,
		Long balance, String title, String content, LocalDateTime transactionTime, Emoticon emoticon, String sender,
		String receiver, Visibility visibility) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.amount = amount;
		this.balance = balance;
		this.title = title;
		this.content = content;
		this.transactionTime = transactionTime;
		this.emoticon = emoticon;
		this.sender = sender;
		this.receiver = receiver;
		this.visibility = visibility;
	}

	public static TransactionDto from(Transaction transaction) {
		return TransactionDto.builder()
			.transactionId(transaction.getTransactionId())
			.transactionType(transaction.getTransactionType())
			.amount(transaction.getAmount())
			.balance(transaction.getBalance())
			.title(transaction.getTitle())
			.content(transaction.getContent())
			.transactionTime(transaction.getTransactionTime())
			.emoticon(transaction.getEmoticon())
			.sender(transaction.getSender().getAccountName())
			.receiver(transaction.getReceiver().getAccountName())
			.categoryName(transaction.getCategory().getCategoryName())
			.visibility(transaction.getVisibility())
			.build();
	}

	public static TransactionDto from(UpdateBalanceDto.Request request) {
		return TransactionDto.builder()
			.transactionType(TransactionType.valueOf(request.getTransactionType()))
			.categoryId(request.getCategoryId())
			.amount(request.getAmount())
			.balance(request.getBalance())
			.title(request.getContent().substring(0, 10))
			.content(request.getContent())
			.emoticon(Emoticon.valueOf(request.getEmoticon()))
			.sender(request.getAccountNumber())
			.receiver(request.getAccountNumber())
			.visibility(Visibility.PRIVATE)
			.build();
	}

}