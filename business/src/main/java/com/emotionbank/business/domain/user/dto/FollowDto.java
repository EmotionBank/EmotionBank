package com.emotionbank.business.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowDto {
	Long followee;
	Long follower;

	public static FollowDto of(Long followee, Long follower) {
		return FollowDto.builder()
			.followee(followee)
			.follower(follower)
			.build();
	}
}
