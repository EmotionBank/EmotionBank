package com.emotionbank.business.api.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserUpdateDto {

	@Builder
	@Getter
	public static class Request {
		String nickname;
	}

}
