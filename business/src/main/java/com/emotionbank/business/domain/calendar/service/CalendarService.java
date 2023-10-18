package com.emotionbank.business.domain.calendar.service;

import java.util.List;

import com.emotionbank.business.domain.calendar.dto.CalendarDto;
import com.emotionbank.business.domain.calendar.dto.CalendarSearchDto;

public interface CalendarService {
	List<CalendarDto> getCalendarByMonth(CalendarSearchDto calendarSearchDto);

}
