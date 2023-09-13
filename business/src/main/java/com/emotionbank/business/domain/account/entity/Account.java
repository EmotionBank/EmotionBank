package com.emotionbank.business.domain.account.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Column(name = "account_name")
	private String accountName;
	@Column(name = "account_number")
	private String accountNumber;

	@Column(name="created_time")
	private LocalDateTime createdTime;

	@OneToOne(mappedBy = "sender")
	private Transaction sender;

	@OneToOne(mappedBy = "receiver")
	private Transaction receiver;
}
