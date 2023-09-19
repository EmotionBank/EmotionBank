package com.emotionbank.business.api.calendar.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emotionbank.business.api.calendar.dto.GetCalendarDto;
import com.emotionbank.business.domain.calendar.dto.CalendarDto;
import com.emotionbank.business.domain.calendar.dto.CalendarSearchDto;
import com.emotionbank.business.domain.calendar.service.CalendarService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

	private final CalendarService calendarService;

	@GetMapping
	public ResponseEntity<GetCalendarDto.Response> getCalendarByMonth(@RequestBody GetCalendarDto.Request request) {
		List<CalendarDto> calendars = calendarService.getCalendarByMonth(
			CalendarSearchDto.from(request));
		return ResponseEntity.ok(GetCalendarDto.Response.from(calendars));
	}
}
