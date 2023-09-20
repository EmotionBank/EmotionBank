package com.emotionbank.business.domain.calendar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.calendar.dto.CalendarDto;
import com.emotionbank.business.domain.calendar.dto.CalendarSearchDto;
import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.calendar.repository.CalendarRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

	private final AccountRepository accountRepository;
	private final CalendarRepository calendarRepository;

	@Override
	public List<CalendarDto> getCalendarByMonth(CalendarSearchDto calendarSearchDto) {
		Account account = accountRepository.findByAccountNumber(calendarSearchDto.getAccountNumber())
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		List<Calendar> calendars = calendarRepository.findByYearAndMonth(
			account.getAccountId(), calendarSearchDto.getYear(), calendarSearchDto.getMonth());
		return calendars.stream()
			.map(CalendarDto::from)
			.collect(Collectors.toList());
	}
}
