package com.emotionbank.business.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.emotionbank.business.domain.account.dto.AccountDto;
import com.emotionbank.business.domain.agreement.entity.Agreement;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
	private final Long userId;

	private final String nickname;

	private final LocalDate birthDay;

	private final String email;

	private final SocialType socialType;

	private final LocalDate createdTime;

	private final LocalDate lastLoginTime;

	private final LocalDate withdrawalTime;

	private final AccountDto account;

	private final List<Agreement> agreement;

	private final List<Category> category;

	private final String image;

	private final int following;
	private final int follower;

	@Builder
	public UserDto(Long userId, String nickname, LocalDate birthDay, String email, SocialType socialType,
		LocalDate createdTime, LocalDate lastLoginTime, LocalDate withdrawalTime, AccountDto account,
		List<Agreement> agreement, List<Category> category, String image, int following, int follower) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthDay = birthDay;
		this.email = email;
		this.socialType = socialType;
		this.createdTime = createdTime;
		this.lastLoginTime = lastLoginTime;
		this.withdrawalTime = withdrawalTime;
		this.account = account;
		this.agreement = agreement;
		this.category = category;
		this.image = image;
		this.following = following;
		this.follower = follower;
	}

	public static UserDto of(String nickname, String image) {
		return UserDto.builder()
			.nickname(nickname)
			.image(image)
			.build();
	}

	public static UserDto of(long userId, String nickname) {
		return UserDto.builder()
			.userId(userId)
			.nickname(nickname)
			.build();
	}

	public static UserDto of(Long userId, String nickname, AccountDto accountDto, int following, int follower) {
		return UserDto.builder()
			.userId(userId)
			.nickname(nickname)
			.account(accountDto)
			.following(following)
			.follower(follower)
			.build();
	}

	public static UserDto of(String nickname, Long userId, AccountDto accountDto, int following, int follower) {
		return UserDto.builder()
			.nickname(nickname)
			.userId(userId)
			.account(accountDto)
			.following(following)
			.follower(follower)
			.build();
	}

	public static UserDto from(User user) {
		return UserDto.builder()
			.userId(user.getUserId())
			.birthDay(user.getBirthday())
			.nickname(user.getNickname())
			.image(user.getImage())
			.account(AccountDto.from(user.getAccounts().get(0)))
			.build();
	}

}
