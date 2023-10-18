package com.emotionbank.business.domain.calendar.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.transaction.constant.Emoticon;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long calendarId;

	private LocalDate date;

	@Enumerated(value = EnumType.STRING)
	private Emoticon emoticon;

	private Long amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Builder
	public Calendar(Long calendarId, LocalDate date, Emoticon emoticon, Long amount, Account account) {
		this.calendarId = calendarId;
		this.date = date;
		this.emoticon = emoticon;
		this.amount = amount;
		this.account = account;
	}

	public void updateAmount(Long amount, Emoticon emoticon) {
		if (amount >= this.amount) {
			this.amount = amount;
			this.emoticon = emoticon;
		}
	}

	public static Calendar of(LocalDate localDate, Emoticon emoticon, Long amount, Account account) {
		return Calendar.builder()
			.date(localDate)
			.emoticon(emoticon)
			.amount(amount)
			.account(account)
			.build();
	}

}
