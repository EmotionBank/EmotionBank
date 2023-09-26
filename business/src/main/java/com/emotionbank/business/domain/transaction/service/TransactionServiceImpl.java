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
import com.emotionbank.business.domain.transaction.constant.Visibility;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.dto.TransactionTransferDto;
import com.emotionbank.business.domain.transaction.dto.TransactionUpdateDto;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
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
	private final UserRepository userRepository;

	private static final String transferCategory = "transaction";

	@Transactional
	@Override
	public TransactionDto updateBalance(TransactionDto transactionDto, Long userId) {
		// 계좌 존재 유무 확인
		Account account = accountRepository.findByAccountNumber(transactionDto.getReceiver())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));

		// 유저 정보 조회
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		if (!user.getUserId().equals(account.getUser().getUserId())) {
			throw new BusinessException(USER_NOT_EQUAL);
		}

		// 카테고리 조회
		Category category = categoryRepository.findByUserAndCategoryId(user, transactionDto.getCategoryId())
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
		calendarRepository.findByDateAndAccount(transaction.getTransactionTime().toLocalDate(), account)
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

		if (account.getUser().getUserId() != transactionSearchDto.getUserId()) { // 남의 거래 내역
			List<Transaction> transactions = transactionRepository.findByAccountAndDateAndVisibility(
				account, transactionSearchDto.getStartDate(), transactionSearchDto.getEndDate(), Visibility.PUBLIC);
			List<TransactionDto> transactionList = transactions.stream()
				.map(TransactionDto::from)
				.collect(Collectors.toList());
			return transactionList;
		} else { // 내 거래 내역 
			List<Transaction> transactions = transactionRepository.searchTransactionByAccountAndDate(
				account, transactionSearchDto.getStartDate(), transactionSearchDto.getEndDate());
			List<TransactionDto> transactionList = transactions.stream()
				.map(TransactionDto::from)
				.collect(Collectors.toList());
			return transactionList;
		}

	}

	@Transactional
	@Override
	public long transfer(TransactionTransferDto transactionTransferDto) {
		Account sender = accountRepository.findByAccountId(transactionTransferDto.getSender())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));
		Account receiver = accountRepository.findByAccountId(transactionTransferDto.getReceiver())
			.orElseThrow(() -> new BusinessException(ACCOUNT_NOT_EXIST));

		long amount = transactionTransferDto.getAmount();

		sender.updateBalance(-amount);
		receiver.updateBalance(amount);

		Long balance = sender.getBalance();

		// 잔액이 0원 미만인 경우 거래 취소
		if (balance < 0) {
			throw new BusinessException(BELOW_ZERO_BALANCE);
		}

		// 입금자의 이체 카테고리 가지고 오기.
		Category receiverCategory = categoryRepository.findByUserAndCategoryName(receiver.getUser(), transferCategory)
			.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));
		// 입금 거래 내역 만들기
		Transaction deposit = Transaction.of(TransactionType.DEPOSIT, receiverCategory, amount, receiver.getBalance(),
			sender,
			receiver,
			transactionTransferDto.getEmoticon());
		transactionRepository.save(deposit);
		// 캘린더 당일 기분 및 금액 업데이트
		calendarRepository.findByDateAndAccount(deposit.getTransactionTime().toLocalDate(), receiver)
			.ifPresentOrElse(
				calendar -> calendar.updateAmount(deposit.getAmount(), deposit.getEmoticon()),
				() -> calendarRepository.save(
					Calendar.of(deposit.getTransactionTime().toLocalDate(), deposit.getEmoticon(),
						deposit.getAmount(), receiver))
			);

		// 출금자의 이체 카테고리 가지고 오기.
		Category senderCategory = categoryRepository.findByUserAndCategoryName(sender.getUser(), transferCategory)
			.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));
		// 출금 거래 내역 만들기
		Transaction withdrawl = Transaction.of(TransactionType.WITHDRAWL, senderCategory, amount, balance, sender,
			receiver,
			transactionTransferDto.getEmoticon());
		transactionRepository.save(withdrawl);

		// 캘린더 당일 기분 및 금액 업데이트
		calendarRepository.findByDateAndAccount(withdrawl.getTransactionTime().toLocalDate(), sender)
			.ifPresentOrElse(
				calendar -> calendar.updateAmount(withdrawl.getAmount(), withdrawl.getEmoticon()),
				() -> calendarRepository.save(
					Calendar.of(withdrawl.getTransactionTime().toLocalDate(), withdrawl.getEmoticon(),
						withdrawl.getAmount(), sender))
			);

		return balance;
	}

	@Transactional
	@Override
	public void updateTransaction(TransactionUpdateDto transactionUpdateDto) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionUpdateDto.getTransactionId())
			.orElseThrow(() -> new BusinessException(TRANSACTION_NOT_EXIST));

		User user = userRepository.findById(transactionUpdateDto.getUserId())
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		Category category = categoryRepository.findByUserAndCategoryId(user, transactionUpdateDto.getCategoryId())
			.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));
		log.info(category.getCategoryName());

		transaction.updateCategory(category);
	}

	@Transactional(readOnly = true)
	@Override
	public TransactionDto getTransactionDetail(Long transactionId, Long userId) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionId)
			.orElseThrow(() -> new BusinessException(TRANSACTION_NOT_EXIST));

		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(USER_NOT_FOUND));

		if (Visibility.PRIVATE.equals(transaction.getCategory().getVisibility())) {
			Category category = categoryRepository.findByUserAndCategoryId(user,
					transaction.getCategory().getCategoryId())
				.orElseThrow(() -> new BusinessException(CATEGORY_NOT_EXIST));
			if (userId.equals(category.getUser().getUserId())) {
				return TransactionDto.from(transaction);
			} else {
				throw new BusinessException(TRANSACTION_BAD_REQUEST);
			}
		}
		return TransactionDto.from(transaction);
	}

	private void validateBalance(Account account, Long expectedBalance) {
		if (!expectedBalance.equals(account.getBalance())) {
			throw new BusinessException(BALANCE_NOT_EQUAL);
		}
	}

}
