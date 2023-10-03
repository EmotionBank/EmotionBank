package com.emotionbank.business.api.user.dto;

import java.util.List;

import com.emotionbank.business.domain.user.dto.ReportDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserReportDto {


	@Builder
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Response {
		List<ReportDto.Report> deposits;
		List<ReportDto.Report> withdrawals;
		List<ReportDto.Balance> balances;

		public static Response from(ReportDto reportDto) {
			return Response.builder()
				.deposits(reportDto.getDeposits())
				.withdrawals(reportDto.getWithdrawals())
				.balances(reportDto.getBalances())
				.build();
		}
	}


}
