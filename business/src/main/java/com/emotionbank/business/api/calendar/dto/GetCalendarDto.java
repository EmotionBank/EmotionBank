package com.emotionbank.business.api.calendar.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.emotionbank.business.domain.calendar.dto.CalendarDto;
import com.emotionbank.business.domain.transaction.constant.Emoticon;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GetCalendarDto {
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private String accountNumber;
		private String date;

		@Builder
		public Request(String accountNumber, String date) {
			this.accountNumber = accountNumber;
			this.date = date;
		}
	}

	@Getter
	public static class CalendarInfo {
		private LocalDate date;
		private Emoticon emoticon;

		@Builder
		public CalendarInfo(LocalDate date, Emoticon emoticon) {
			this.date = date;
			this.emoticon = emoticon;
		}

		public static CalendarInfo from(CalendarDto calendarDto){
			return CalendarInfo.builder()
				.date(calendarDto.getDate())
				.emoticon(calendarDto.getEmoticon())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		private List<CalendarInfo> calendarInfoList;

		@Builder
		public Response(List<CalendarInfo> calendarInfoList) {
			this.calendarInfoList = calendarInfoList;
		}

		public static Response from(List<CalendarDto> calendarDtoList){
			return Response.builder()
				.calendarInfoList(calendarDtoList.stream()
					.map(CalendarInfo::from)
					.collect(Collectors.toList()))
				.build();
		}
	}
}
