package com.emotionbank.business.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.user.entity.Agreement;
import com.emotionbank.business.domain.user.entity.Category;
import com.emotionbank.business.domain.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
	private Long userId;

	private String nickname;

	private LocalDate birthDay;

	private String email;

	private SocialType memberType;

	private LocalDate createdTime;

	private LocalDate lastLoginTime;

	private LocalDate withdrawalTime;

	private List<Account> accounts;

	private List<Agreement> agreement;

	private List<Category> category;

	private String image;

	public static UserDto of(String nickname, String image) {
		return UserDto.builder()
			.nickname(nickname)
			.image(image)
			.build();
	}

	public static UserDto from(User user) {
		return UserDto.builder()
			.nickname(user.getNickname())
			.image(user.getImage())
			.build();
	}
}
