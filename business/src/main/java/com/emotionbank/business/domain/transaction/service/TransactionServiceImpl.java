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
import com.emotionbank.business.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;

	@Transactional
	@Override
	public TransactionDto deposit(TransactionDto transactionDto) {
		// sender 계좌 유무 확인
		Account senderAccount = accountRepository.findByAccountNumber(transactionDto.getSender())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));
		
		// receiver 계좌 유무 확인
		Account receiverAccount = accountRepository.findByAccountNumber(transactionDto.getReceiver())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));

		// receiver 계좌에 입금
		receiverAccount.updateBalance(transactionDto.getAmount());

		// 변경된 잔액 불러오기
		Long balance = receiverAccount.getBalance();

		// 거래 내역 저장
		Transaction transaction = Transaction.of(transactionDto,senderAccount, receiverAccount,balance);
		transactionRepository.save(transaction);
		return TransactionDto.from(transaction);
	}
}

