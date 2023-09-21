package com.emotionbank.business.domain.transaction.service;

import static com.emotionbank.business.global.error.ErrorCode.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.calendar.repository.CalendarRepository;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.category.repository.CategoryRepository;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;
	private final CategoryRepository categoryRepository;
	private final CalendarRepository calendarRepository;

	@Transactional
	@Override
	public TransactionDto updateBalance(TransactionDto transactionDto) {
		// 계좌 존재 유무 확인
		Account account = accountRepository.findByAccountNumber(transactionDto.getReceiver())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));

		// 카테고리 조회
		Category category = categoryRepository.findByCategoryId(transactionDto.getCategoryId())
			.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));

		// 잔액 일치 여부 조회
		validateBalance(account, transactionDto.getBalance());

		// 입금, 출금
		if (TransactionType.DEPOSIT.equals(transactionDto.getTransactionType())) {
			account.updateBalance(transactionDto.getAmount());
		} else if (TransactionType.WITHDRAWL.equals(transactionDto.getTransactionType())) {
			account.updateBalance(-transactionDto.getAmount());
		}
		Long balance = account.getBalance();

		// 잔액이 0원 미만인 경우 거래 취소
		if (balance < 0) {
			throw new BusinessException(BELOW_ZERO_BALANCE);
		}

		// 거래 내역 저장
		Transaction transaction = Transaction.of(transactionDto, category, account, balance);
		transactionRepository.save(transaction);

		// 캘린더 당일 기분 및 금액 업데이트
		calendarRepository.findByDate(transaction.getTransactionTime().toLocalDate())
			.ifPresentOrElse(
				calendar -> calendar.updateAmount(transaction.getAmount(), transaction.getEmoticon()),
				() -> calendarRepository.save(
					Calendar.of(transaction.getTransactionTime().toLocalDate(), transaction.getEmoticon(),
						transaction.getAmount(), account))
			);

		return TransactionDto.from(transaction);
	}

	@Transactional(readOnly = true)
	@Override
	public List<TransactionDto> getTransactions(TransactionSearchDto transactionSearchDto) {
		Account account = accountRepository.findByAccountId(transactionSearchDto.getAccountId())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));

		// 계좌번호와 날짜로 거래내역 조회
		List<Transaction> transactions = transactionRepository.searchTransactionByAccountAndDate(
			account, transactionSearchDto.getStartDate(), transactionSearchDto.getEndDate());

		List<TransactionDto> transactionList = transactions.stream()
			.map(TransactionDto::from)
			.collect(Collectors.toList());
		return transactionList;
	}

	private void validateBalance(Account account, Long expectedBalance) {
		if (!expectedBalance.equals(account.getBalance())) {
			throw new BusinessException(BALANCE_NOT_EQUAL);
		}
	}

}
