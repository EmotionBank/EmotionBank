package com.emotionbank.business.domain.account.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.user.entity.User;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "account_name")
	private String accountName;
	@Column(name = "account_number")
	private String accountNumber;

	private Long balance;
	@Column(name = "created_time")
	private LocalDateTime createdTime;

	@OneToMany(mappedBy = "sender")
	private List<Transaction> sender;

	@OneToMany(mappedBy = "receiver")
	private List<Transaction> receiver;

	@OneToMany(mappedBy = "account")
	private List<Calendar> calendars;

	public void updateBalance(Long amount) {
		this.balance += amount;
	}

	public void updateAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Builder
	public Account(User user, String accountName, String accountNumber, Long balance,
		LocalDateTime createdTime) {
		this.user = user;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.createdTime = createdTime;
	}

	public static Account of(String accountName, User user) {
		return Account.builder()
			.user(user)
			.accountName(accountName)
			.accountNumber(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMM-ddHH-mmssSSS")))
			.balance(0L)
			.createdTime(LocalDateTime.now())
			.build();
	}
}