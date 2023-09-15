package com.emotionbank.business.api.user.dto.response;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDto {
	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UserSearchResponseDto {
		private String nickname;
		private String image;

		public static UserResponseDto.UserSearchResponseDto of(UserDto.UserSearchResultDto userSearchResultDto) {
			return UserSearchResponseDto.builder()
				.nickname(userSearchResultDto.getNickname())
				.image(userSearchResultDto.getImage())
				.build();
		}
	}
}
