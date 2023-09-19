package com.emotionbank.business.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.category.entity.Category;
import com.emotionbank.business.domain.user.constant.SocialType;
import com.emotionbank.business.domain.user.entity.Agreement;

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

	private final List<Account> accounts;

	private final List<Agreement> agreement;

	private final List<Category> category;

	private final String image;

	@Builder
	public UserDto(Long userId, String nickname, LocalDate birthDay, String email, SocialType socialType,
		LocalDate createdTime, LocalDate lastLoginTime, LocalDate withdrawalTime, List<Account> accounts,
		List<Agreement> agreement, List<Category> category, String image) {
		this.userId = userId;
		this.nickname = nickname;
		this.birthDay = birthDay;
		this.email = email;
		this.socialType = socialType;
		this.createdTime = createdTime;
		this.lastLoginTime = lastLoginTime;
		this.withdrawalTime = withdrawalTime;
		this.accounts = accounts;
		this.agreement = agreement;
		this.category = category;
		this.image = image;
	}
}
