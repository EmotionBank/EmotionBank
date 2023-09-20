package com.emotionbank.business.domain.calendar.dto;

import java.time.LocalDate;

import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.transaction.constant.Emoticon;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CalendarDto {

	private Long calendarId;

	private LocalDate date;

	private Emoticon emoticon;

	private Long amount;

	private String accountNumber;

	@Builder
	public CalendarDto(Long calendarId, LocalDate date, Emoticon emoticon, Long amount, String accountNumber) {
		this.calendarId = calendarId;
		this.date = date;
		this.emoticon = emoticon;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}

	public static CalendarDto from(Calendar calendar){
		return CalendarDto.builder()
			.calendarId(calendar.getCalendarId())
			.date(calendar.getDate())
			.emoticon(calendar.getEmoticon())
			.amount(calendar.getAmount())
			.accountNumber(calendar.getAccount().getAccountNumber())
			.build();
	}
}
