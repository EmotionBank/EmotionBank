package com.emotionbank.business.api.agreement.dto;

import java.util.List;

import com.emotionbank.business.domain.agreement.dto.AgreementDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TermsAgreementDto {

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class Request {
		private Long termsId;
		private String state;

		@Builder
		public Request(Long termsId, String state) {
			this.termsId = termsId;
			this.state = state;
		}
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class RequestList {
		private List<Request> requests;

		@Builder
		public RequestList(List<Request> requests) {
			this.requests = requests;
		}
	}

	@Getter
	public static class Response {
		private final Long agreementId;
		private final Long termsId;
		private final String title;
		private final String content;
		private final String mandatory;
		private final String state;
		private final String agreementTime;

		@Builder
		public Response(Long agreementId, Long termsId, String title, String content, String mandatory, String state,
			String agreementTime) {
			this.agreementId = agreementId;
			this.termsId = termsId;
			this.title = title;
			this.content = content;
			this.mandatory = mandatory;
			this.state = state;
			this.agreementTime = agreementTime;
		}

		public static Response from(AgreementDto agreementDto) {
			return Response.builder()
				.agreementId(agreementDto.getAgreementId())
				.termsId(agreementDto.getTermsId())
				.title(agreementDto.getTitle())
				.content(agreementDto.getContent())
				.mandatory(agreementDto.getMandatory())
				.state(agreementDto.getState())
				.agreementTime(agreementDto.getAgreementTime())
				.build();
		}
	}

	@Getter
	public static class ResponseList {
		private final List<Response> agreements;

		@Builder
		public ResponseList(List<Response> agreements) {
			this.agreements = agreements;
		}

		public static ResponseList from(List<Response> responses) {
			return ResponseList.builder()
				.agreements(responses)
				.build();
		}
	}

}
