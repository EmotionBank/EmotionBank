package com.emotionbank.business.api.user.dto.response;

import com.emotionbank.business.domain.user.dto.UserSearchResultDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSearchResponseDto {
	private String nickname;
	private String image;

	@Builder
	public UserSearchResponseDto(String nickname, String image) {
		this.nickname = nickname;
		this.image = image;
	}

	public static UserSearchResponseDto of(UserSearchResultDto userSearchResultDto) {
		return UserSearchResponseDto.builder()
			.nickname(userSearchResultDto.getNickname())
			.image(userSearchResultDto.getImage())
			.build();
	}
}
