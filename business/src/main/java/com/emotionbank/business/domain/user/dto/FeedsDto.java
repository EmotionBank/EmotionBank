package com.emotionbank.business.domain.user.dto;

import java.util.List;

import com.emotionbank.business.domain.transaction.constant.Emoticon;
import com.emotionbank.business.domain.transaction.entity.Transaction;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FeedsDto {

	List<FeedDto> feeds;

	public static FeedsDto of(List<FeedDto> feeds) {
		return FeedsDto.builder()
			.feeds(feeds)
			.build();
	}

	@Getter
	@Builder
	public static class FeedDto {
		String nickname;
		Emoticon emoticon;
		Long userId;

		public static FeedDto of(Transaction transaction) {
			return FeedDto.builder()
				.nickname(transaction.getSender().getUser().getNickname())
				.emoticon(transaction.getEmoticon())
				.userId(transaction.getSender().getUser().getUserId())
				.build();
		}
	}
}
