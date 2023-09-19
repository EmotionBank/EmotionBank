package com.emotionbank.business.domain.calendar.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.emotionbank.business.domain.calendar.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	@Query("select c from Calendar c where c.account.accountId = :accountId and year(c.date) = :year and month(c.date) = :month")
	List<Calendar> findByYearAndMonth(Long accountId, int year, int month);

	Optional<Calendar> findByDate(LocalDate date);
}
