package com.emotionbank.business.api.user.dto.response;

import java.time.LocalDate;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
	@Data
	@Builder
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
