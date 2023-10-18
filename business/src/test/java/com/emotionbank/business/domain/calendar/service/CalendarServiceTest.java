package com.emotionbank.business.domain.calendar.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.account.service.AccountService;
import com.emotionbank.business.domain.account.service.AccountServiceImpl;
import com.emotionbank.business.domain.calendar.dto.CalendarDto;
import com.emotionbank.business.domain.calendar.dto.CalendarSearchDto;
import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.calendar.repository.CalendarRepository;
import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;

@SpringBootTest
class CalendarServiceTest {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CalendarRepository calendarRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	@DisplayName("월별 캘린더 조회")
	void getCalendarByYearAndMonth() {
		//Given
		AccountService accountService = new AccountServiceImpl(accountRepository, userRepository);
		CalendarService calendarService = new CalendarServiceImpl(accountRepository, calendarRepository);
		assertNotNull(accountService);
		assertNotNull(calendarService);
		User user = userRepository.save(User.builder()
			.nickname("TEST NAME")
			.birthday(LocalDate.now())
			.role(Role.USER)
			.socialId("123123")
			.socialType(SocialType.KAKAO)
			.build());
		String accountName = "테스트용계좌";
		AccountDto accountDto = accountService.createAccount(user.getUserId(), accountName);
		String accountNumber = accountDto.getAccountNumber();
		Account account = accountRepository.findByAccountNumber(accountNumber)
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		for (int i = 0; i < 20; i++) {
			calendarRepository.save(
				Calendar.builder()
					.account(account)
					.amount(i * 10000L)
					.emoticon(Emoticon.HAPPY)
					.date(LocalDate.of(2023, 9, 19).plusDays(i))
					.build());
		}

		// When
		CalendarSearchDto calendarSearchDto = CalendarSearchDto.builder()
			.accountId(accountDto.getAccountId())
			.year(2023)
			.month(9)
			.build();
		List<CalendarDto> calendarByMonth = calendarService.getCalendarByMonth(calendarSearchDto);

		// Then
		assertEquals(calendarByMonth.size(), 12);
		for (CalendarDto calendarDto : calendarByMonth) {
			assertEquals(2023, calendarDto.getDate().getYear());
			assertEquals(Month.SEPTEMBER, calendarDto.getDate().getMonth());
		}
	}

	@Test
	@DisplayName("날짜별 조회")
	void getCalendarByDate() {
		//Given
		AccountService accountService = new AccountServiceImpl(accountRepository, userRepository);
		CalendarService calendarService = new CalendarServiceImpl(accountRepository, calendarRepository);
		assertNotNull(accountService);
		assertNotNull(calendarService);
		User user = userRepository.save(User.builder()
			.nickname("TEST NAME")
			.birthday(LocalDate.now())
			.role(Role.USER)
			.socialId("123123")
			.socialType(SocialType.KAKAO)
			.build());
		String accountName = "테스트용계좌";
		AccountDto accountDto = accountService.createAccount(user.getUserId(), accountName);
		String accountNumber = accountDto.getAccountNumber();
		Account account = accountRepository.findByAccountNumber(accountNumber)
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		for (int i = 0; i < 20; i++) {
			calendarRepository.save(
				Calendar.builder()
					.account(account)
					.amount(i * 10000L)
					.emoticon(Emoticon.HAPPY)
					.date(LocalDate.of(2023, 9, 19).plusDays(i))
					.build());
		}

		// When
		LocalDate date = LocalDate.of(2023, 10, 3);
		Calendar calendar = calendarRepository.findByDateAndAccount(date, account)
			.orElseThrow(() -> new BusinessException(ErrorCode.CALENDAR_NOT_EXIST));

		// Then
		assertEquals(calendar.getAmount(), 140000L);
	}

}
