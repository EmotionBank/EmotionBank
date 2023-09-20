package com.emotionbank.business.domain.calendar.dto;

import com.emotionbank.business.api.calendar.dto.GetCalendarDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CalendarSearchDto {

	private Long accountId;
	private Integer year;
	private Integer month;

	@Builder
	public CalendarSearchDto(Long accountId, Integer year, Integer month) {
		this.accountId = accountId;
		this.year = year;
		this.month = month;
	}


	public static CalendarSearchDto from(GetCalendarDto.Request request){
		return CalendarSearchDto.builder()
			.accountId(request.getAccountId())
			.year(request.getYear())
			.month(request.getMonth())
			.build();
	}
}
