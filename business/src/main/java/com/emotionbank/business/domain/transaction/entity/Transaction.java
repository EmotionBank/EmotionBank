package com.emotionbank.business.domain.transaction.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.transaction.dto.Emoticon;
import com.emotionbank.business.domain.transaction.dto.TransactionType;
import com.emotionbank.business.domain.transaction.dto.Visibility;
import com.emotionbank.business.domain.user.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private int amount;
	private int balance;
	private String title;
	private String content;

	private LocalDateTime transactionTime;
	private Emoticon emoticon;

	@OneToOne
	@JoinColumn(name = "sender")
	private Account sender;

	@OneToOne
	@JoinColumn(name = "receiver")
	private Account receiver;

	private Visibility visibility;

}
