package com.emotionbank.business.domain.user.dto;

import com.emotionbank.business.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchResultDto {
	private String nickname;
	private String image;

	public static UserSearchResultDto of(String nickname, String image) {
		return UserSearchResultDto.builder()
			.nickname(nickname)
			.image(image)
			.build();
	}

	public static UserSearchResultDto from(User user) {
		return UserSearchResultDto.builder()
			.nickname(user.getNickname())
			.image(user.getImage())
			.build();
	}
}
