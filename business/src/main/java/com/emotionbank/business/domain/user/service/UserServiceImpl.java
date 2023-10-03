package com.emotionbank.business.domain.user.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.emotionbank.business.domain.user.dto.ReportDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emotionbank.business.api.user.dto.UserReportDto;
import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
import com.emotionbank.business.domain.calendar.entity.Calendar;
import com.emotionbank.business.domain.calendar.repository.CalendarRepository;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.category.repository.CategoryRepository;
import com.emotionbank.business.domain.transaction.constant.TransactionType;
import com.emotionbank.business.domain.transaction.entity.Transaction;
import com.emotionbank.business.domain.transaction.repository.TransactionRepository;
import com.emotionbank.business.domain.user.constant.Role;
import com.emotionbank.business.domain.user.dto.FollowDto;
import com.emotionbank.business.domain.user.dto.UserDto;
import com.emotionbank.business.domain.user.entity.Follow;
import com.emotionbank.business.domain.user.entity.User;
import com.emotionbank.business.domain.user.repository.FollowRepository;
import com.emotionbank.business.domain.user.repository.UserRepository;
import com.emotionbank.business.global.error.ErrorCode;
import com.emotionbank.business.global.error.exception.BusinessException;
import com.emotionbank.business.global.jwt.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final FollowRepository followRepository;
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final CategoryRepository categoryRepository;
	private final CalendarRepository calendarRepository;

	@Override
	public List<UserDto> searchUser(String userNickname, Pageable pageable) {
		List<User> users = userRepository.findByNicknameContains(userNickname, pageable);
		List<UserDto> userDtos = users.stream()
			.map(UserDto::from)
			.collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void followUser(FollowDto followDto) {
		User follower = userRepository.findById(followDto.getFollower())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		User followee = userRepository.findById(followDto.getFollowee())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		followRepository.findByFolloweeAndFollower(followee, follower)
			.ifPresentOrElse(
				followRepository::delete,
				() -> followRepository.save(Follow.of(follower, followee))
			);
	}

	@Override
	public List<UserDto> getFollowees(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		List<Follow> follows = followRepository.findByFollower(user);
		List<User> users = follows.stream().map(Follow::getFollowee).collect(Collectors.toList());
		List<UserDto> userDtos = users.stream().map(UserDto::from).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public List<UserDto> getFollowers(Long userId) {
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		List<Follow> follows = followRepository.findByFollowee(user);
		List<User> users = follows.stream().map(Follow::getFollower).collect(Collectors.toList());
		List<UserDto> userDtos = users.stream().map(UserDto::from).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserInfo(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		UserDto userDto = UserDto.from(user);
		return userDto;
	}

	@Override
	public UserDto getMyProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		Account account = accountRepository.findByUser(user)
			.orElseThrow(() -> new BusinessException(ErrorCode.ACCOUNT_NOT_EXIST));
		AccountDto accountDto = AccountDto.from(account);
		int following = followRepository.findByFollower(user).size();
		int follower = followRepository.findByFollowee(user).size();
		return UserDto.of(user.getNickname(), accountDto, following, follower);
	}

	@Override
	public UserDto getOtherProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		int following = followRepository.findByFollower(user).size();
		int follower = followRepository.findByFollowee(user).size();
		return UserDto.of(user.getNickname(), user.getUserId(), AccountDto.from(user.getAccounts().get(0)), following,
			follower);
	}

	@Override
	@Transactional
	public void updateUser(UserDto userDto) {
		User user = userRepository.findById(userDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		if (checkDuplicateNickname(userDto.getNickname())) {
			throw (new BusinessException(ErrorCode.NICKNAME_DUPLICATE));
		}
		user.updateNickname(userDto.getNickname());

	}

	@Override
	public boolean checkDuplicateNickname(String nickname) {
		return !userRepository.existsByNickname(nickname);
	}

	@Override
	public boolean checkAdminRole(UserInfoDto userInfoDto) {
		User user = userRepository.findById(userInfoDto.getUserId())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		if (!(user.getRole() == Role.ADMIN)) {
			throw new BusinessException(ErrorCode.TERMS_CREATE_UNAUTHORIZED);
		}
		return true;
	}

	@Override
	public ReportDto getReport(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
		List<Category> categories = categoryRepository.findByUser(user);
		List<ReportDto.Report> deposits = new ArrayList<>();
		List<ReportDto.Report> withdrawals = new ArrayList<>();
		List<ReportDto.Balance> balances = new ArrayList<>();

		long withdrawalSum = 0;
		long depositSum = 0;

		for (Category category : categories) {
			List<Transaction> transactions = transactionRepository.findByCategory(category);
			for (Transaction transaction : transactions) {
				if (TransactionType.WITHDRAWL.equals(transaction.getTransactionType())) {
					withdrawalSum += transaction.getAmount();
				} else if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
					depositSum += transaction.getAmount();
				}
			}
			deposits.add(ReportDto.Report.of(category.getCategoryName(), depositSum));
			withdrawals.add(ReportDto.Report.of(category.getCategoryName(), withdrawalSum));
		}
		LocalDate now = LocalDate.now();
		
		for (int i = 1; i <= now.getDayOfMonth(); i++) {
			Optional<Calendar> calendar = calendarRepository.findByDateAndAccount(
				LocalDate.of(now.getYear(), now.getMonth(), i), user.getAccounts()
					.get(0));
			if (calendar.isPresent()) {
				// todo: 기분에 따라서 amount 조절하기
				balances.add(ReportDto.Balance.builder()
					.amount(calendar.get().getAmount())
					.day(i)
					.build());
			}
		}
		return ReportDto.of(deposits, withdrawals, balances);
	}
}
