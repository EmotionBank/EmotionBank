package com.emotionbank.business.domain.calendar.dto;

import java.time.LocalDate;
import java.util.StringTokenizer;

import com.emotionbank.business.api.calendar.dto.GetCalendarDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CalendarSearchDto {

	private String accountNumber;
	private Integer year;
	private Integer month;

	@Builder
	public CalendarSearchDto(String accountNumber, Integer year, Integer month) {
		this.accountNumber = accountNumber;
		this.year = year;
		this.month = month;
	}

	public static CalendarSearchDto from(GetCalendarDto.Request request){
		StringTokenizer st = new StringTokenizer(request.getDate(), "-");
		return CalendarSearchDto.builder()
			.accountNumber(request.getAccountNumber())
			.year(Integer.parseInt(st.nextToken()))
			.month(Integer.parseInt(st.nextToken()))
			.build();
	}
}
