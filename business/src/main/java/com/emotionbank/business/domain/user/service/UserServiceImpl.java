package com.emotionbank.business.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.account.repository.AccountRepository;
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
		return UserDto.of(user.getNickname(), following, follower);
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

}
