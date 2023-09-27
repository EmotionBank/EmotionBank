package com.emotionbank.business.api.user.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class UserReportDto {
	List<Report> deposits;
	List<Report> withdrawals;
	List<Balance> balances;

	public static UserReportDto of(List<Report> deposits, List<Report> withdrawals, List<Balance> balances) {
		return UserReportDto.builder()
			.deposits(deposits)
			.withdrawals(withdrawals)
			.balances(balances)
			.build();
	}

	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		List<Report> deposits;
		List<Report> withdrawals;
		List<Balance> balances;

		public static Response from(UserReportDto userReportDto) {
			return Response.builder()
				.deposits(userReportDto.getDeposits())
				.withdrawals(userReportDto.getWithdrawals())
				.balances(userReportDto.getBalances())
				.build();
		}
	}

	@Builder
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
