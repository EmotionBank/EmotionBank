package com.emotionbank.business.api.user.dto;

import java.time.LocalDate;

import com.emotionbank.business.domain.user.dto.UserDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserInfoDto {

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		String nickname;
		LocalDate birthday;
		String accountNumber;
		String accountName;

		public static Response from(UserDto userDto) {
			return Response.builder()
				.nickname(userDto.getNickname())
				.birthday(userDto.getBirthDay())
				.accountNumber(userDto.getAccounts().get(0).getAccountNumber())
				.accountName(userDto.getAccounts().get(0).getAccountName())
				.build();
		}
	}

}
