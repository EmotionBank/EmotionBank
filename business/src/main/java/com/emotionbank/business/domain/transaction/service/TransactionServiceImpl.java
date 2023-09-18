package com.emotionbank.business.domain.transaction.service;

import static com.emotionbank.business.global.error.ErrorCode.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.domain.user.entity.Category;
import com.emotionbank.business.domain.user.repository.CategoryRepository;
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

		// 거래 내역 저장
		Transaction transaction = Transaction.of(transactionDto, category, account, balance);
		transactionRepository.save(transaction);
		return TransactionDto.from(transaction);
	}

	private void validateBalance(Account account, Long expectedBalance) {
		if (!expectedBalance.equals(account.getBalance())) {
			throw new BusinessException(BALANCE_NOT_EQUAL);
		}
	}

}
