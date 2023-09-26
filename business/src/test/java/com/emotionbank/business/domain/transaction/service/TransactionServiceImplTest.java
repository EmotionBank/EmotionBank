package com.emotionbank.business.domain.transaction.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.calendar.repository.CalendarRepository;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.category.repository.CategoryRepository;
import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.dto.TransactionDto;
import com.emotionbank.business.domain.transaction.dto.TransactionSearchDto;
import com.emotionbank.business.domain.transaction.dto.TransactionTransferDto;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.exception.BusinessException;

class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@Mock
	private TransactionRepository transactionRepository;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private CategoryRepository categoryRepository;

	@Mock
	private CalendarRepository calendarRepository;
	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("입금을 성공적으로 처리한다")
	@Transactional
	void depositSuccessfully() {
		// Given
		TransactionDto transactionDto = TransactionDto.builder()
			.transactionType(TransactionType.DEPOSIT)
			.amount(1000L)
			.balance(20000L)
			.categoryId(1L)
			.sender("777-7777")
			.receiver("777-7777")
			.build();

		Account account = Account.builder()
			.accountNumber("777-7777")
			.balance(20000L)
			.build();

		Category category = Category.builder()
			.categoryId(1L)
			.categoryName("회사")
			.build();

		Calendar calendar = Calendar.builder()
			.date(LocalDate.now().atStartOfDay().toLocalDate())
			.amount(10000L)
			.account(account)
			.build();

		when(accountRepository.findByAccountNumber("777-7777"))
			.thenReturn(Optional.of(account));
		when(categoryRepository.findByCategoryId(1L))
			.thenReturn(Optional.of(category));
		when(calendarRepository.findByDateAndAccount(LocalDate.now().atStartOfDay().toLocalDate(), account))
			.thenReturn(Optional.of(calendar));

		// When
		TransactionDto resultDto = transactionService.updateBalance(transactionDto);

		// Then
		assertThat(resultDto).isNotNull();
		assertThat(resultDto.getBalance()).isEqualTo(21000L);
	}

	@Test
	@DisplayName("거래 내역을 성공적으로 조회한다")
	@Transactional(readOnly = true)
	void getTransactionsTest() {
		// Given
		TransactionSearchDto transactionSearchDto = TransactionSearchDto.builder()
			.accountId(1L)
			.startDate(Date.valueOf(LocalDate.now()))
			.endDate(Date.valueOf(LocalDate.now()))
			.build();

		Account account = Account.builder()
			.accountNumber("777-7777")
			.balance(20000L)
			.build();

		Transaction transaction = Transaction.builder()
			.transactionTime(LocalDateTime.now())
			.transactionType(TransactionType.DEPOSIT)
			.sender(account)
			.receiver(account)
			.build();

		List<Transaction> transactionList = Arrays.asList(transaction, transaction);

		when(accountRepository.findByAccountId(1L))
			.thenReturn(Optional.of(account));
		when(transactionRepository.searchTransactionByAccountAndDate(account, Date.valueOf(LocalDate.now()),
			Date.valueOf(LocalDate.now())))
			.thenReturn((transactionList));

		// When
		List<TransactionDto> resultDtoList = transactionService.getTransactions(transactionSearchDto);

		// Then
		assertThat(resultDtoList).isNotNull();
		assertThat(resultDtoList).hasSize(2);
	}

	@Test
	@DisplayName("이체 한다")
	void transfer() {
		Account sender = Account.builder()
			.balance(20000L)
			.build();
		Account receiver = Account.builder()
			.balance(0L)
			.build();
		Emoticon emoticon = Emoticon.HAPPY;

		TransactionTransferDto transactionTransferDto = TransactionTransferDto.builder()
			.sender(1L)
			.receiver(2L)
			.amount(10000L)
			.emoticon(emoticon)
			.build();

		User sendPerson = User.builder().build();
		User receivePerson = User.builder().build();
		when(userRepository.findById(transactionTransferDto.getSender())).thenReturn(Optional.ofNullable(sendPerson));
		when(userRepository.findById(transactionTransferDto.getReceiver())).thenReturn(
			Optional.ofNullable(receivePerson));

		when(accountRepository.findByUser(sendPerson)).thenReturn(Optional.of(sender));
		when(accountRepository.findByUser(receivePerson)).thenReturn(Optional.of(receiver));

		Category receiverCategory = Category.builder().build();
		Category senderCategory = Category.builder().build();
		when(categoryRepository.findByUserAndCategoryName(receiver.getUser(), "transaction")).thenReturn(
			Optional.ofNullable(receiverCategory));
		when(categoryRepository.findByUserAndCategoryName(sender.getUser(), "transaction")).thenReturn(
			Optional.ofNullable(senderCategory));

		long balance = transactionService.transfer(transactionTransferDto);

		Assertions.assertThat(balance).isEqualTo(10000L);
		Assertions.assertThat(receiver.getBalance()).isEqualTo(10000L);

		verify(accountRepository, times(2)).findByUser(any());
		verify(transactionRepository, times(2)).save(any(Transaction.class));
		verify(calendarRepository, times(2)).findByDateAndAccount(any(), any());

	}

	@Test
	@DisplayName("가진돈 보다 더 큰 금액을 이체한다.")
	void transferOutOfMoney() {
		Account sender = Account.builder()
			.balance(20000L)
			.build();
		Account receiver = Account.builder()
			.balance(0L)
			.build();
		Emoticon emoticon = Emoticon.HAPPY;

		TransactionTransferDto transactionTransferDto = TransactionTransferDto.builder()
			.sender(1L)
			.receiver(2L)
			.amount(30000L)
			.emoticon(emoticon)
			.build();

		when(accountRepository.findByAccountId(transactionTransferDto.getSender())).thenReturn(Optional.of(sender));
		when(accountRepository.findByAccountId(transactionTransferDto.getReceiver())).thenReturn(Optional.of(receiver));

		assertThrows(BusinessException.class, () -> {
			transactionService.transfer(transactionTransferDto);
		});

	}
}