package com.emotionbank.business.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowDto {
	String followee;
	String follower;

	public static FollowDto of(String followee, String follower) {
		return FollowDto.builder()
			.followee(followee)
			.follower(follower)
			.build();
	}
}
