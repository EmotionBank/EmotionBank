package com.emotionbank.business.domain.user.dto;

import java.time.LocalDate;
import java.util.List;

import com.emotionbank.business.domain.account.entity.Account;
import com.emotionbank.business.domain.user.entity.Agreement;
import com.emotionbank.business.domain.user.entity.Category;
import com.emotionbank.business.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UserDto {
	private Long userId;

	private String nickname;

	private LocalDate birthDay;

	private String email;

	private Type memberType;

	private LocalDate createdTime;

	private LocalDate lastLoginTime;

	private LocalDate withdrawalTime;

	private List<Account> accounts;

	private List<Agreement> agreement;

	private List<Category> category;

	private String image;

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserSearchResultDto {
		private String nickname;
		private String image;

		public static UserSearchResultDto of(String nickname, String image) {
			return UserSearchResultDto.builder()
				.nickname(nickname)
				.image(image)
				.build();
		}

		public static UserDto.UserSearchResultDto from(User user) {
			return UserDto.UserSearchResultDto.builder()
				.nickname(user.getNickname())
				.image(user.getImage())
				.build();
		}
	}
}
