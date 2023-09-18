package com.emotionbank.business.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowDto {
	String followee;
	String follower;
}
