package com.emotionbank.business.domain.transaction.dto;

import com.emotionbank.business.domain.transaction.constant.Emoticon;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TransactionTransferDto {
	Long sender;
	Long receiver;
	Long amount;
	Emoticon emoticon;

	public static TransactionTransferDto of(long sender, long receiver, long amount, Emoticon emoticon) {
		return TransactionTransferDto.builder()
			.sender(sender)
			.receiver(receiver)
			.amount(amount)
			.emoticon(emoticon)
			.build();
	}
}
