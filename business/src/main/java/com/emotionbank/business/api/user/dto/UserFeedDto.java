package com.emotionbank.business.api.user.dto;

import com.emotionbank.business.domain.user.dto.FeedsDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserFeedDto {

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		FeedsDto feedList;

		public static Response from(FeedsDto feedsDto) {
			return Response.builder()
				.feedList(feedsDto)
				.build();
		}
	}
}
