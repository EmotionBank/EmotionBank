package com.emotionbank.business.api.transaction.dto;

import com.emotionbank.business.domain.transaction.constant.Emoticon;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TransferDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	@Builder
	public static class Request {
		private Long receiver;
		private Long amount;
		private Emoticon emoticon;
	}

	public static class Response {

	}
}
