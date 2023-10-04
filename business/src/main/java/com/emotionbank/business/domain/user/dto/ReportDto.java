package com.emotionbank.business.domain.user.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportDto {
	List<ReportDto.Report> deposits;
	List<ReportDto.Report> withdrawals;
	List<ReportDto.Balance> balances;

	public static ReportDto of(List<Report> deposits, List<Report> withdrawals, List<Balance> balances) {
		return ReportDto.builder()
			.deposits(deposits)
			.withdrawals(withdrawals)
			.balances(balances)
			.build();
	}

	@Builder
	@Getter
	public static class Report {
		String categoryName;
		Long amount;

		public static Report of(String categoryName, long amount) {
			return Report.builder()
				.categoryName(categoryName)
				.amount(amount)
				.build();
		}
	}

	@Builder
	@Getter
	public static class Balance {
		int day;
		Long amount;

		public static Balance of(int day, long amount) {
			return Balance.builder()
				.day(day)
				.amount(amount)
				.build();
		}
	}
}
