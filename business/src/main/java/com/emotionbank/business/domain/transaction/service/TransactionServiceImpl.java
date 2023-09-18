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
		// sender 계좌 유무 확인
		Account senderAccount = accountRepository.findByAccountNumber(transactionDto.getSender())
			.orElseThrow(() -> new BusinessException(SENDER_ACCOUNT_NOT_EXIST));

		// receiver 계좌 유무 확인
		Account receiverAccount = accountRepository.findByAccountNumber(transactionDto.getReceiver())
			.orElseThrow(() -> new BusinessException(RECEIVER_ACCOUNT_NOT_EXIST));

		// receiver 카테고리 조회
		Category category = categoryRepository.findByCategoryId(transactionDto.getCategoryId())
			.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));

		// 입금, 출금
		Long balance = 0L;
		if (TransactionType.DEPOSIT.equals(transactionDto.getTransactionType())) {
			// 잔액 일치하는지 비교
			if (!receiverAccount.getBalance().equals(transactionDto.getBalance())) {
				throw new BusinessException(BALANCE_NOT_EQUAL);
			}
			receiverAccount.updateBalance(transactionDto.getAmount());
			// 변경된 잔액 불러오기
			balance = receiverAccount.getBalance();
		} else if (TransactionType.WITHDRAWL.equals(transactionDto.getTransactionType())) {
			// 잔액 일치하는지 비교
			if (!senderAccount.getBalance().equals(transactionDto.getBalance())) {
				throw new BusinessException(BALANCE_NOT_EQUAL);
			}
			senderAccount.updateBalance(-transactionDto.getAmount());
			balance = senderAccount.getBalance();
		}
		// 거래 내역 저장
		Transaction transaction = Transaction.of(transactionDto, category, senderAccount, receiverAccount, balance);
		transactionRepository.save(transaction);
		return TransactionDto.from(transaction);
	}

}
