package com.emotionbank.business.domain.transaction.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.user.entity.Category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@Column(name = "transaction_type")
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private Long amount;
	private Long balance;
	private String title;
	private String content;

	@Column(name="transaction_time")
	private LocalDateTime transactionTime;

	private Emoticon emoticon;

	@OneToOne
	@JoinColumn(name = "sender")
	private Account sender;

	@OneToOne
	@JoinColumn(name="receiver")
	private Account receiver;

	private Visibility visibility;

	@Builder
	public Transaction(Long transactionId, TransactionType transactionType, Category category, Long amount, Long balance,
		String title,
		String content, LocalDateTime transactionTime, Emoticon emoticon, Account sender, Account receiver,
		Visibility visibility) {
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.category = category;
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
	public static Transaction of(TransactionDto transactionDto,Account sender, Account receiver,Long balance){
		return Transaction.builder()
			.transactionId(transactionDto.getTransactionId())
			.transactionType(transactionDto.getTransactionType())
			.category(transactionDto.getCategory())
			.amount(transactionDto.getAmount())
			.balance(balance)
			.title(transactionDto.getTitle())
			.content(transactionDto.getContent())
			.transactionTime(transactionDto.getTransactionTime())
			.emoticon(transactionDto.getEmoticon())
			.sender(sender)
			.receiver(receiver)
			.visibility(transactionDto.getVisibility())
			.build();
	}
}